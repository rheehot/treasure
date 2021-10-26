package spring.mall.domain.item;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Item {

    private Long id;
    @NotEmpty
    private String itemName;
    @NotEmpty
    private String content;
    @NotEmpty
    private Integer price;
    @NotEmpty
    private Integer quantity;
    @NotEmpty
    private String state;

    public Item(String itemName, String content, Integer price, Integer quantity, String state) {
        this.itemName = itemName;
        this.content = content;
        this.price = price;
        this.quantity = quantity;
        this.state = state;
    }

    public Item() {

    }
}
