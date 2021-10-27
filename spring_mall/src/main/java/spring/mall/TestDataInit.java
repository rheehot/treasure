package spring.mall;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.mall.domain.item.Item;
import spring.mall.domain.item.ItemRepository;
import spring.mall.domain.member.Member;
import spring.mall.domain.member.MemberRepository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @PostConstruct
    public void LoginInit() {
        Member member = new Member();
        member.setLoginId("admin");
        member.setPassword("1234");
        member.setName("test");

        memberRepository.save(member);
    }

    @PostConstruct
    public void ItemInit() {

        itemRepository.save(new Item("Item A", "content A", 10000, 100, "판매중"));
        itemRepository.save(new Item("Item B", "content B", 20000, 200, "판매중"));
        itemRepository.save(new Item("Item C", "content C", 30000, 300, "판매중"));
        itemRepository.save(new Item("Item D", "content D", 40000, 400, "판매중"));
        itemRepository.save(new Item("Item E", "content E", 50000, 500, "품절"));

    }

}
