package com.shop.controller;

import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemSearchDto;
import com.shop.entity.Item;
import com.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

//    @GetMapping(value = "/admin/item/new")
//    public String itemForm() {
//        return "/item/itemForm";
//    }

    @GetMapping(value = "/admin/item/new")
    public String itemForm(Model model) {
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "item/itemForm";
    }

    @PostMapping(value = "/admin/item/new")
    public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult, Model model, @RequestParam(name = "itemImgFile") List<MultipartFile> itemImgFileList) {
        if (bindingResult.hasErrors()) { //상품 등록 시 필수 값이 없다면 상품 페이지로 전환.
            return "item/itemForm";
        }
        //상품 등록 시 첫번째 이미지가 없다면 에러 메세지와 함께 상품 등록 페이지로 전환.
        //첫 번째 이미지는 메인 페이지에서 보여줄 이미지 이기 때문에 필수 값.
        if (itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "item/itemForm";
        }

        try {
            //상품 저장 로직 호출. 매개변수로 상품 정보와 상품 이미지를 담고 있느 itemImgFileList를 넘김.
            itemService.saveItem(itemFormDto, itemImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "item/itemForm";
        }
        //정상적으로 등록되면 메인 페이지로 이동.
        return "redirect:/";
    }

    @GetMapping(value = "/admin/item/{itemId}")
    public String itemDtl(@PathVariable("itemId") Long itemId, Model model) {
        try {
            //조회한 상품 데이터를 모델에 담아서 뷰로 전달.
            ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
            model.addAttribute("itemFormDto", itemFormDto);
        } catch (EntityNotFoundException e) {
            //존재하지 않을 경우, 에러메세지를 담아서 상품 등록페이지로 이동.
            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
            model.addAttribute("itemFormDto", new ItemFormDto());
            return "item/itemForm";
        }
        return "item/itemForm";
    }

    @PostMapping(value = "/admin/item/{itemId}")
    public String itemUpdate(@Valid ItemFormDto itemFormDto, BindingResult bindingResult, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, Model model, @PathVariable String itemId) {
        if (bindingResult.hasErrors()) {
            return "item/itemForm";
        }
        if (itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "item/itemForm";
        }

        try {
            itemService.updateItem(itemFormDto, itemImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
            return "item/itemForm";
        }

        return "redirect:/";
    }

    //value에 상품 관리 화면 진입 시 URL에 페이지 번호가 없는 경우, 페이지 번호가 있는 경우 2가지 매핑.
    @GetMapping(value = {"/admin/items", "/admin/items/{page}"})
    public String itemManage(ItemSearchDto itemSearchDto, @PathVariable("page") Optional<Integer> page, Model model) {
        //페이징을 위해서 PageRequest.of 메소드 사용해서 Pageable 객체 생성. 첫번째 파라미터 : 조회할 페이지 번호, 두번째 파라미터 : 한 번에 가지고 올 데이터 수
        //페이지 번호가 있으면 해당 페이지를 조회, 페이지 번호가 없으면 0페이지 조회.
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 10);
        //조회 조건과 페이징 보를 파라미터로 넘겨서 Page<Item> 객체 반환 받기.
        Page<Item> items = itemService.getAdminItemPage(itemSearchDto, pageable);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        //페이지 번호 개수 지정.
        model.addAttribute("maxPage", 5);
        return "item/itemMng";
    }

    @GetMapping("/item/{itemId}")
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId) {
        ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
        model.addAttribute("item", itemFormDto);
        return "item/itemDtl";
    }
}
