package com.TruNarrative.TruNarrativeSpringExercise.dto;

import lombok.Data;

@Data
public class CompanyDTO {

    public CompanyDTO(){}

    private String company_number;
    private String company_type;
    private String title;
    private String company_status;
    private String date_of_creation;
    private AddressDTO address;
    private OfficerDTO[] officers;


    @Override
    public String toString() {
        return "CompanyDTO{" +
                "company_number='" + company_number + '\'' +
                ", company_type='" + company_type + '\'' +
                ", title='" + title + '\'' +
                ", company_status='" + company_status + '\'' +
                ", date_of_creation='" + date_of_creation + '\'' +
                ", address=" + address +
                '}';
    }
}
