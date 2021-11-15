package com.shop.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@Log
@RequiredArgsConstructor
public class FileService {


    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception {
        String uuid = UUID.randomUUID().toString(); //파일 중복을 해결하기 위해 사용.
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = uuid + extension; //UUID로 받은 값과, 원래 파일 이름의 확장자를 조합해서 저장될 파일 이름 생성.
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;
        //FileOutputStream = 바이트 단위의 출력을 내보내는 클래스. 생성자로 파일이 저장될 위치와 파일의 이름을 넘겨 파일에 쓸 파일 출력 스트림 생성.
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        //fileData를 파일 출력 스트림에 입력.
        fos.write(fileData);
        fos.close();
        //업로드 된 파일 이름 반환.
        return savedFileName;
    }

    public void deleteFile(String filePath) throws Exception {
        //파일이 저장된 경로를 이용하여 파일 객체 생성.
        File deleteFile = new File(filePath);
        //파일이 존재하면 파일을 삭제.
        if (deleteFile.exists()) {
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        } else {
            log.info("파일이 존재하지 않습니다.");
        }
    }

}
