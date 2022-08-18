package com.TruNarrative.TruNarrativeSpringExercise.dto;

import lombok.Data;

@Data
public class OfficerDTO {
    private String name;
    private String officer_role;
    private String appointed_on;
    private AddressDTO address;

    @Override
    public String toString() {
        return "OfficerDTO{" +
                "name='" + name + '\'' +
                ", officer_role='" + officer_role + '\'' +
                ", appointed_on='" + appointed_on + '\'' +
                ", address=" + address +
                '}';
    }
}
