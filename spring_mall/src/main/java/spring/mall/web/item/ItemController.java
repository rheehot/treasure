package spring.mall.web.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mall.domain.item.Item;
import spring.mall.domain.item.ItemRepository;

import java.io.IOException;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemRepository itemRepository;


    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();

        model.addAttribute("items", items);

        return "items/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "items/item";
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute ItemForm form) {
        return "items/addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes) throws IOException {


        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setContent(form.getContent());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());
        item.setState(form.getState());
        itemRepository.save(item);

        redirectAttributes.addAttribute("itemId", item.getId());


        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "items/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/items/{itemId}";
    }


}
