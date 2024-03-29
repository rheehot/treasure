package com.shop.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // application.properties에서 설정한 uploadPath 프로퍼티 값 읽어오기.
    // 요거 경로 바꾸기.
    @Value("${uploadPath}")
    String uploadPath;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;  // S3 버킷 이름

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //웹 브라우저에 입력하는 url에 /images로 시작하는 경우, uploadPath에 설정한 폴더를 기준으로 파일을 읽어오도록 설정.
        registry.addResourceHandler("/images/**")
                //로컬 컴퓨터에 저장된 파일을 읽어올 root 경로.
                .addResourceLocations(bucket);
    }
}
