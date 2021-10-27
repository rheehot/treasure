package spring.mall.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @Transactional
    public void testItem() throws Exception {
        //given
        Item item = new Item();
        item.setItemName("testName");
        //when
        Long savedId = itemRepository.save(item);
        Item findItem = itemRepository.find(savedId);
        //then
        Assertions.assertThat(findItem.getItemName()).isEqualTo(item.getItemName());
        Assertions.assertThat(findItem.getId()).isEqualTo(item.getId());
    }
}