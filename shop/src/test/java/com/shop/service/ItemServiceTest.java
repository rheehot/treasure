//package com.shop.service;
//
//import com.shop.constant.ItemSellStatus;
//import com.shop.dto.ItemFormDto;
//import com.shop.entity.Item;
//import com.shop.entity.ItemImg;
//import com.shop.repository.ItemImgRepository;
//import com.shop.repository.ItemRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//@Transactional
//public class ItemServiceTest {
//
//    @Autowired
//    ItemService itemService;
//
//    @Autowired
//    ItemRepository itemRepository;
//
//    @Autowired
//    ItemImgRepository itemImgRepository;
//
//    //MockMultipartFile 클래스를 이용해서 가짜 MultipartFile 리스트를 만들어서 반환해주는 메소드.
//    List<MultipartFile> createMultipartFiles() throws Exception {
//        List<MultipartFile> multipartFileList = new ArrayList<>();
//
//        for (int i = 0; i < 5; i++) {
//            String path = "/Users/kimsunho/project/upload_path";
//            String imageName = "image" + i + ".jpg";
//            MockMultipartFile multipartFile = new MockMultipartFile(path, imageName, "image/jpg", new byte[]{1, 2, 3, 4});
//            multipartFileList.add(multipartFile);
//        }
//        return multipartFileList;
//    }
//
//    @Test
//    @DisplayName("상품 등록 테스트")
//    @WithMockUser(username = "ADMIN", roles = "ADMIN")
//    void saveItem() throws Exception {
//        // 상품 등록 화면에서 입력 받는 상품 데이터를 세팅.
//        ItemFormDto itemFormDto = new ItemFormDto();
//        itemFormDto.setItemName("테스트 상품");
//        itemFormDto.setItemSellStatus(ItemSellStatus.SELL);
//        itemFormDto.setItemDetail("테스트 상품 상세 내용");
//        itemFormDto.setPrice(10000);
//        itemFormDto.setStockNumber(10);
//        List<MultipartFile> multipartFileList = createMultipartFiles();
//        //상품 데이터와 이미지 정보를 파라미터로 넘겨서 저장 후, 저장된 상품의 아이디 값을 반환 값으로 리턴.
//        Long itemId = itemService.saveItem(itemFormDto, multipartFileList);
//
//        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
//        Item item = itemRepository.findById(itemId)
//                .orElseThrow(EntityNotFoundException::new);
//        //입력한 상품 데이터와 실제 저장된 상품 데이터가 같은지 확인.
//        assertEquals(itemFormDto.getItemName(), item.getItemName());
//        assertEquals(itemFormDto.getItemSellStatus(), item.getItemSellStatus());
//        assertEquals(itemFormDto.getItemDetail(), item.getItemDetail());
//        assertEquals(itemFormDto.getPrice(), item.getPrice());
//        assertEquals(itemFormDto.getStockNumber(), item.getStockNumber());
//        assertEquals(multipartFileList.get(0).getOriginalFilename(), itemImgList.get(0).getOriImgName());
//
//    }
//
//}
