package com.project.ecommerce_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    public String street;
    public String city;
    public String state;
    public String zipcode;
    public String phone;
}
