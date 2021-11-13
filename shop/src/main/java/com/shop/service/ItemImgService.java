package com.shop.service;

import com.amazonaws.services.s3.AmazonS3;
import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
@Component
public class ItemImgService {

    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;

    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception {
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //파일 업로드
        if (!StringUtils.isEmpty(oriImgName)) {
            //사용자가 상품 이미지를 등록했다면, 저장할 경로와 파일 이름, 바이트 배열을 파일 업로드 파라미터로 uploadFile 메소드 호출.
            //호출 결과 로컬에 저장된 파일의 이름을 imgName에 저장.
            imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            imgUrl = "/images/item/" + imgName;

        }

        //상품 이미지 정보 저장.
        // imgName : 실제 로컬에 저장된 상품 이미지 파일 이름.
        // oriImgName : 업로드 했던 상품 이미지 파일의 원래 이름.
        // imgUrl : 업로드 결과 로컬에 저장된 상품 이미지 파일을 불러오는 경로.
        itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        itemImgRepository.save(itemImg);
    }

    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception {
        //상품 이미지를 수정한 경우, 상품 이미지를 업데이트.
        if (!itemImgFile.isEmpty()) {
            //상품 이미지 아이디를 이용해서 기존에 저장한 상품 이미지 엔티티 조회.
            ItemImg savedItemImg = itemImgRepository.findById(itemImgId)
                    .orElseThrow(EntityNotFoundException::new);
            //기존 이미지 파일이 있을 경우, 삭제.
            if (!StringUtils.isEmpty(savedItemImg.getImgName())) {
                fileService.deleteFile(itemImgLocation + "/" + savedItemImg.getImgName());
            }
            String oriImgName = itemImgFile.getOriginalFilename();
            //업데이트한 상품 이미지 파일 업로드.
            String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            String imgUrl = "/images/item/" + imgName;
            //변경된 상품 이미지 정보 세팅.
            savedItemImg.updateItemImg(oriImgName, imgName, imgUrl);
        }
    }
}
