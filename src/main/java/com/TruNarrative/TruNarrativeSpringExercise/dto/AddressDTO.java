package com.TruNarrative.TruNarrativeSpringExercise.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private String locality;
    private String postal_code;
    private String premises;
    private String address_line_1;
    private String country;

    @Override
    public String toString() {
        return "AddressDTO{" +
                "locality='" + locality + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", premises='" + premises + '\'' +
                ", address_line_1='" + address_line_1 + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}