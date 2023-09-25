package inhatc.spring.shopjin.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inhatc.spring.shopjin.constant.ItemSellStatus;
import inhatc.spring.shopjin.entity.Item;
import inhatc.spring.shopjin.entity.QItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static inhatc.spring.shopjin.entity.QItem.item;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EntityManager em;

    public void createItemList(){
        for (int i = 1; i<= 100 ; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .stockNumber(100 + i)
                    .itemDetail("테스트 상품 상세 설명" + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();

            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("queryDsl 테스트")
    public void querydslTest(){
        createItemList();
        JPAQueryFactory query = new JPAQueryFactory(em);

        List<Item> itemList = query.selectFrom(item)
                .where(item.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(item.itemDetail.like("%" + "1" + "%"))
                .orderBy(item.price.desc())
                .fetch();

        itemList.forEach(System.out::println);
    }

    @Test
    @DisplayName("JPQL 테스트")
    public void findByDetailTest(){
        createItemList();
        itemRepository.findByDetail("1")
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("Native 테스트")
    public void findByDetailNativeTest(){
        createItemList();
        itemRepository.findByDetailNative("1")
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("OrderBy 테스트")
    public void findByPriceLessThanOrderByPriceDescTest(){
        createItemList();
        itemRepository.findByPriceLessThanOrderByPriceDesc(10005)
                .forEach((item -> System.out.println(item)));
//        forEach(System.out::println);
    }

    @Test
    @DisplayName("OR 테스트")
    public void findByItemNmOrItemDetailTest(){
        createItemList();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품2", "테스트 상품 상세 설명8");
        itemList.forEach((item -> {
            System.out.println(item);
        }));
    }

    @Test
    @DisplayName("상품 이름 검색 테스트")
    public void findByItemNmTest(){
        createItemList();

        itemRepository
                .findByItemNm("테스트 상품1")
                .forEach((item)->{
                    System.out.println(item);
                });
//        for (Item item : itemList) {
//            System.out.println(item);
//        }
//        itemList.forEach(System.out::println);
    }

    @Test
    @DisplayName("상품 생성 테스트")
    public void createItemTest(){
        Item item = Item.builder()
                .itemNm("테스트 상품")
                .price(10000)
                .stockNumber(100)
                .itemDetail("테스트 상품 상세 설명")
                .itemSellStatus(ItemSellStatus.SELL)
                .regTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        System.out.println("=====================item : " + item);
        Item savedItem = itemRepository.save(item);
        System.out.println("=====================savedItem : " + savedItem);
    }
    //과제 재고 150이상 and 상품명에 8이 들어간
    @Test
    @DisplayName("과제 쿼리메소드(재고, 이름으로 검색)")
    public void findByStockNumberGreaterThanEqualAndItemNmLikeTest(){
        createItemList();

        List<Item> itemList = itemRepository.findByStockNumberGreaterThanEqualAndItemNmLike(150, "%8%");
        itemList.forEach((item) -> {System.out.println(item);});

    }

    @Test
    @DisplayName("과제 JPQL(재고, 이름으로 검색)")
    public void findByStockNumberAndItemNmTest(){
        createItemList();

        List<Item> itemList = itemRepository.findByStockNumberAndItemNm(150, "8");
        itemList.forEach((item) -> {
            System.out.println(item);
        });
    }

    @Test
    @DisplayName("과제 Native(재고, 이름으로 검색)")
    public void findByStockNumberAndItemNmNativeTest(){
        createItemList();

        List<Item> itemList = itemRepository.findByStockNumberAndItemNmNative(150, "8");
        itemList.forEach((item) -> {
            System.out.println(item);
        });
    }

    @Test
    @DisplayName("과제 queryDsl(재고, 이름으로 검색)")
    public void querydslhwTest(){
        createItemList();
        JPAQueryFactory query = new JPAQueryFactory(em);

        List<Item> itemList = query.selectFrom(item)
                .where(item.stockNumber.goe(150))
                .where(item.itemNm.like("%" + "8" + "%"))
                .fetch();

        itemList.forEach(System.out::println);
    }
}