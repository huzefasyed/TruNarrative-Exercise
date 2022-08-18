package com.TruNarrative.TruNarrativeSpringExercise.helper;

import com.TruNarrative.TruNarrativeSpringExercise.dto.AddressDTO;
import com.TruNarrative.TruNarrativeSpringExercise.dto.CompanyDTO;
import com.TruNarrative.TruNarrativeSpringExercise.dto.OfficerDTO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONHelper {
    public CompanyDTO[] jsonResponseToCompanies(String rawJson) throws JSONException {
        JSONObject rawJsonObj = new JSONObject(rawJson);

        JSONArray items = rawJsonObj.optJSONArray("items");

        if (items == null) {
            return new CompanyDTO[0];
        }

        CompanyDTO[] companies = new CompanyDTO[items.length()];

        for (int i = 0; i < items.length(); i++) {
            JSONObject obj = items.getJSONObject(i);
            CompanyDTO company = new CompanyDTO();
            company.setCompany_number(obj.optString("company_number", ""));
            company.setCompany_type(obj.optString("company_type", ""));
            company.setTitle(obj.optString("title"));
            company.setCompany_status(obj.optString("company_status", ""));
            company.setDate_of_creation(obj.optString("date_of_creation", ""));

            AddressDTO address = new AddressDTO();
            JSONObject adr = obj.getJSONObject("address");
            address.setAddress_line_1(adr.optString("address_line_1", ""));
            address.setCountry(adr.optString("country", ""));
            address.setLocality(adr.optString("locality", ""));
            address.setPostal_code(adr.optString("postal_code", ""));
            address.setPremises(adr.optString("premises", ""));
            company.setAddress(address);
            companies[i] = company;
        }

        return companies;
    }

    public OfficerDTO[] jsonResponseToOfficers(String rawJson) throws JSONException {
        JSONObject rawJsonObj = new JSONObject(rawJson);

        JSONArray items = rawJsonObj.optJSONArray("items");

        if (items == null) {
            return new OfficerDTO[0];
        }
        OfficerDTO[] officers = new OfficerDTO[items.length()];

        for (int i = 0; i < items.length(); i++) {
            JSONObject obj = items.getJSONObject(i);

            OfficerDTO officerDTO = new OfficerDTO();
            officerDTO.setName(obj.optString("name", ""));
            officerDTO.setOfficer_role(obj.optString("officer_role", ""));
            officerDTO.setAppointed_on(obj.optString("appointed_on", ""));

            AddressDTO address = new AddressDTO();
            JSONObject adr = obj.getJSONObject("address");
            address.setAddress_line_1(adr.optString("address_line_1", ""));
            address.setCountry(adr.optString("country", ""));
            address.setLocality(adr.optString("locality", ""));
            address.setPostal_code(adr.optString("postal_code", ""));
            address.setPremises(adr.optString("premises", ""));
            officerDTO.setAddress(address);
            officers[i] = officerDTO;
        }
        return officers;
    }
}
