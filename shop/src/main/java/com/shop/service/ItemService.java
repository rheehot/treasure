package com.shop.service;

import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemImgDto;
import com.shop.entity.Item;
import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;


    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {

        //상품 등록
        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        //이미지 등록
        for (int i = 0; i < itemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);
            if (i == 0) { // 첫번째 이미지일 경우, 대표 상품 이미지 여부 값을 Y로, 나머지는 N.
                itemImg.setRepimgYn("Y");
            } else {
                itemImg.setRepimgYn("N");
            }
            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i)); // 상품 이미지 정보 저장.
        }
        return item.getId();
    }

    @Transactional(readOnly = true) //상품 정보를 읽어오는 트랙잭션 읽기 전용으로 설정.
    public ItemFormDto getItemDtl(Long itemId) {
        //해당 상품의 이미지 조회.
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();
        //조회한 ItemImg엔티티를 ItemImgDto 객체로 만들어서 리스트에 추가.
        for (ItemImg itemImg : itemImgList) {
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }
        //상품 아이디를 통해 상품 엔티티 조회. 존재하지 않으면 EntityNotFoundException 발생.
        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        ItemFormDto itemFormDto = ItemFormDto.of(item);
        itemFormDto.setItemImgDtoList(itemImgDtoList);
        return itemFormDto;
    }

    //상품 수정
    public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {
        //상품 등록 화면으로부터 전달 받은 상품 아이디를 이용한 상품 엔티티 조회.
        Item item = itemRepository.findById(itemFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        //상품 등록 화면으로 전달 받은 itemFormDto를 통해서 상품 엔티티 업데이트.
        item.updateItem(itemFormDto);
        //상품 이미지 아이디 리스트 조회.
        List<Long> itemImgIds = itemFormDto.getItemImgIds();

        //이미지 등록
        //상품 이미지 업데이트를 위해 updateItemImg() 메소드에 상품 이미지 아이디, 상품 이미지 파일 정보를 파라미터로 전달.
        for (int i = 0; i < itemImgFileList.size(); i++) {
            itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
        }
        return item.getId();
    }
}
