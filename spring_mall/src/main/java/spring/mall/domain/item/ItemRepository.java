package spring.mall.domain.item;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ItemRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Item item) {
        em.persist(item);

        return item.getId();
    }

    public Item find(Long id) {
        return em.find(Item.class, id);
    }

}
