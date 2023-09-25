package inhatc.spring.shopjin.entity;

import inhatc.spring.shopjin.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    @Column(name = "item_id") // 속성명을 바꿔버림
    private Long id;

    @Column(nullable = false, length = 50)
    private String itemNm; //대문자 있으면 끊는 듯 ex)item_nm

    private int price;

    private int stockNumber;

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
}
