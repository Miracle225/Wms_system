package com.example.wms_system.models;

import com.example.wms_system.dto.GoodAcceptanceDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "good_acceptance")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GoodAcceptance {
  /*
  CREATE TABLE Receipt_of_goods
(
  id                    INT  NOT NULL ,
  date_of_reception     DATE  NOT NULL ,
  quantity              INTEGER  NOT NULL ,
  price                 FLOAT  NULL ,
  provider_id           INT  NOT NULL ,
);
   */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "acception_date", columnDefinition = "DATE")
    @NotNull(message = "acception date can`t be null")
    private Date acceptionDate;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "price",  nullable = false)
    private Float price;
    @Column(name = "accepts_volume",  nullable = false)
    private Float acceptsVolume;
    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    /*@OneToMany(fetch = FetchType.LAZY,mappedBy = "acceptance", cascade = CascadeType.ALL)
    private List<AcceptanceItem> acceptanceItems = new ArrayList<>();*/

    public GoodAcceptance(Date acceptionDate,
                          Integer quantity,
                          Float price,
                          Float acceptsVolume,
                          Provider provider) {
        this.acceptionDate = acceptionDate;
        this.quantity = quantity;
        this.price = price;
        this.acceptsVolume = acceptsVolume;
        this.provider = provider;
    }

    public GoodAcceptance updateFields(GoodAcceptanceDto acceptanceDto){
        if(acceptanceDto.getAcceptionDate()!=null){
            acceptionDate = acceptanceDto.getAcceptionDate();
        }
        if(acceptanceDto.getAcceptsVolume()!=null){
            acceptsVolume = acceptanceDto.getAcceptsVolume();
        }
        if(acceptanceDto.getPrice()!=null){
           price = acceptanceDto.getPrice();
        }
        if(acceptanceDto.getQuantity()!=null){
           quantity = acceptanceDto.getQuantity();
        }
        if(acceptanceDto.getProvider()!=null){
            provider = acceptanceDto.getProvider();
        }
        return this;
    }
}
