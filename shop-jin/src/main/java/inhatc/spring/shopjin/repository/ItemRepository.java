package inhatc.spring.shopjin.repository;

import inhatc.spring.shopjin.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemNm(String itemNm);

    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    List<Item> findByPriceLessThanOrderByPriceDesc(int price);

    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByDetail(@Param("itemDetail") String itemDetail);    //JPQL Item.java에서 가져오는듯 그래서 속성명이나 테이블명 Item, itemDetail이렇게 가도됌

    @Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price asc", nativeQuery = true)
    List<Item> findByDetailNative(@Param("itemDetail") String itemDetail);    //Native DB에 직접 쿼리 때림 그래서 말그대로 DB의 속성명과 테이블명인 item, item_detail이렇게 가야함
}
