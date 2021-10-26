package spring.mall.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        Item item = new Item("itemA", "테스트 상품 A", 10000, 10, "판매중");

        Item savedItem = itemRepository.save(item);

        Item findItem = itemRepository.findById(item.getId());

        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        Item item1 = new Item("itemA", "따듯한 A", 10000, 10, "판매중");
        Item item2 = new Item("itemB", "차가운 B", 20000, 20, "판매중");
        Item item3 = new Item("itemC", "섬뜩한 C", 30000, 30, "판매중");
        Item item4 = new Item("itemD", "날카로운 D", 40000, 0, "품절");

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);

        List<Item> result = itemRepository.findAll();

        assertThat(result.size()).isEqualTo(4);
        assertThat(result).contains(item1, item2, item3, item4);
    }

    @Test
    void updateItem() {
        Item item = new Item("itemA", "따듯한 A", 10000, 10, "판매중");
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        Item updateParam = new Item("item2", "차가운 B", 20000, 20, "판매중");
        itemRepository.update(itemId, updateParam);

        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getContent()).isEqualTo(updateParam.getContent());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
        assertThat(findItem.getState()).isEqualTo(updateParam.getState());


    }


}
