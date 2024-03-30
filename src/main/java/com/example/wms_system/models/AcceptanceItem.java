package com.example.wms_system.models;

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
    @ManyToOne
    @JoinColumn(name = "acceptance_id", nullable = false)
    private GoodAcceptance acceptance;
    @OneToOne
    @JoinColumn(name = "good_id",nullable = false)
    private Good good;

    public AcceptanceItem(Integer expectedQuantity,
                          Integer receivedQuantity) {
        this.expectedQuantity = expectedQuantity;
        this.receivedQuantity = receivedQuantity;
    }
}
