package spring.mall.web.item;

import lombok.Data;

@Data
public class ItemForm {

    private Long itemId;
    private String itemName;
    private String content;
    private Integer price;
    private Integer quantity;
    private String state;
}
