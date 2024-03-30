package com.example.wms_system.models;

import com.example.wms_system.dto.ProviderDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "provider")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Provider {
    /*
    CREATE TABLE provider
(
  id                    INT  NOT NULL ,
  web_site              VARCHAR2(20)  NULL ,
  contact_data          LONG VARCHAR  NOT NULL
);
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "web_site", columnDefinition = "TEXT")
    private String webSite;
    @Column(name = "contact_data",columnDefinition = "VARCHAR(255)")
    private String contactData;

    public Provider(String webSite,
                    String contactData) {
        this.webSite = webSite;
        this.contactData = contactData;
    }

    public Provider updateFields(ProviderDto providerDto){
        if(providerDto.getContactData()!=null){
            contactData = providerDto.getContactData();
        }
        if(providerDto.getWebSite()!=null){
            webSite = providerDto.getWebSite();
        }
        return this;
    }
}
