package com.example.wms_system.dto;

import com.example.wms_system.models.Provider;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProviderDto {
    @NotNull(message = "Contact data can`t be null")
    private String contactData;
    private String webSite;

    public Provider toProviderEntity(){
        return new Provider(
                webSite,
                contactData);
    }

}
