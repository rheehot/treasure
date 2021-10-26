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
    public void itemInit() {

        itemRepository.save(new Item("itemA", "아이템 에이", 10000, 10, "판매중"));
        itemRepository.save(new Item("itemB", "아이템 비", 20000, 20, "판매중"));
        itemRepository.save(new Item("itemC", "아이템 씨", 30000, 30, "판매중"));
        itemRepository.save(new Item("itemD", "아이템 디", 40000, 40, "품절"));
    }
}
