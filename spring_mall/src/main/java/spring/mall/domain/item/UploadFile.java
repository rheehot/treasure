package spring.mall.domain.item;

import lombok.Data;

@Data
public class UploadFile {

    private String uploadFileName; // 고객 업로드한 파일
    private String storeFileName; // 서버 내부에서 관리하는 파일

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}

