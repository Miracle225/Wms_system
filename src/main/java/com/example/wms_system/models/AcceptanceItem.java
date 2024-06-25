package com.example.wms_system.models;

import com.example.wms_system.dto.AcceptanceItemDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "acceptance_item")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AcceptanceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "expected_quantity")
    private Integer expectedQuantity;
    @Column(name = "received_quantity")
    private Integer receivedQuantity;
    @Column(name = "is_distributed")
    private Boolean isDistributed;
    @ManyToOne
    @JoinColumn(name = "acceptance_id", nullable = false)
    private GoodAcceptance acceptance;
    @ManyToOne
    @JoinColumn(name = "good_id",nullable = false)
    private Good good;

    public AcceptanceItem(Integer expectedQuantity,
                          Integer receivedQuantity,
                          GoodAcceptance goodAcceptance,
                          Good good) {
        this.expectedQuantity = expectedQuantity;
        this.receivedQuantity = receivedQuantity;
        this.acceptance = goodAcceptance;
        this.good = good;
        this.isDistributed = false;
    }

    public AcceptanceItem updateFields(AcceptanceItemDto itemDto) {
        if(itemDto.getExpectedQuantity()!=null){
            expectedQuantity = itemDto.getExpectedQuantity();
        }
        if(itemDto.getReceivedQuantity()!=null){
            receivedQuantity = itemDto.getReceivedQuantity();
        }
        if(itemDto.getAcceptance()!=null){
            acceptance = itemDto.getAcceptance();
        }
        if(itemDto.getGood()!=null){
            good = itemDto.getGood();
        }
        return this;
    }
}
