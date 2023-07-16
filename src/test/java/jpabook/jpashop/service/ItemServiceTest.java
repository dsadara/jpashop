package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 상품등록() throws Exception {
        //given
        Item item = new Album();
        item.setName("Graduation");
        item.setStockQuantity(10);

        //when
        Long saveId = itemService.saveItem(item);

        //then
        assertEquals(item, itemRepository.findOne(saveId));
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품_부족_예외() throws Exception {
        //given
        Item item = new Album();
        item.setName("Graduation");
        item.setStockQuantity(1);

        //when
        Long saveId = itemService.saveItem(item);
        item.removeStock(2);

        //then
        Assert.fail("예외가 발생해야 함.");
    }
}