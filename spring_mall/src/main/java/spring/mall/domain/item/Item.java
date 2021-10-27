package spring.mall.domain.item;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(length = 50)
    private String itemName;
    @NotEmpty
    @Column(length = 255)
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
