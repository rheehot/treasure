package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CsController {

    @GetMapping("/cs/list")
    public String list() {
        return "cs/list";
    }
}
