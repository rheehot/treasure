package com.shop.service;

import com.shop.entity.ItemImg;
import com.shop.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@Transactional
@RequiredArgsConstructor
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
            imgUrl = "/images/item" + imgName;
        }

        //상품 이미지 정보 저장.
        // imgNAme : 실제 로컬에 저장된 상품 이미지 파일 이름.
        // oriImgName : 업로드 했던 상품 이미지 파일의 원래 이름.
        // imgUrl : 업로드 결과 로컬에 저장된 상품 이미지 파일을 불러오는 경로.
        itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        itemImgRepository.save(itemImg);
    }
}
