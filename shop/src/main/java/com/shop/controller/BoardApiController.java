package com.shop.controller;

import com.shop.exception.CustomException;
import com.shop.exception.ErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BoardApiController {

    @GetMapping("/test")
    public String test() {
        throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
    }
}
