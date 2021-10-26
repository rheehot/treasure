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
        Item item = new Item();
        item.setItemName("후드후드");
        item.setContent("따듯한 후드후드");
        item.setPrice(10000);
        item.setQuantity(10);
        item.setState("판매중");
        itemRepository.save(item);
    }
}
