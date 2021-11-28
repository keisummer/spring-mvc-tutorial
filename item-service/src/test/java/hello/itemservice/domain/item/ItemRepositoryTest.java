package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("ItemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);
        Item findItem = itemRepository.findById(item.getId());

        //then
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("ItemA", 10000, 10);
        Item item2 = new Item("ItemB", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> items = itemRepository.findAll();

        //then
        assertThat(items).hasSize(2);
        assertThat(items).contains(item1, item2);
    }

    @Test
    void update() {
        //given
        Item item = new Item("ItemA", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long savedId = savedItem.getId();

        Item updateParam = new Item("ItemB", 20000, 20);

        //when
        itemRepository.update(savedId, updateParam);

        //then
        Item findItem = itemRepository.findById(savedId);
        assertThat(findItem.getItemName()).isEqualTo("ItemB");
        assertThat(findItem.getPrice()).isEqualTo(20000);
        assertThat(findItem.getQuantity()).isEqualTo(20);
    }
}