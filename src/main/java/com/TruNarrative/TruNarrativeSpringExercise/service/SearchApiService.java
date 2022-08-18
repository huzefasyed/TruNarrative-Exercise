package com.TruNarrative.TruNarrativeSpringExercise.service;

import com.TruNarrative.TruNarrativeSpringExercise.dto.CompanyDTO;
import com.TruNarrative.TruNarrativeSpringExercise.dto.OfficerDTO;
import com.TruNarrative.TruNarrativeSpringExercise.dto.RequestDTO;
import com.TruNarrative.TruNarrativeSpringExercise.dto.ResponseDTO;
import com.TruNarrative.TruNarrativeSpringExercise.helper.JSONHelper;
import org.json.JSONException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SearchApiService {
    private final String API_KEY = "IoVV8jaWtJX5CRRIYqyR542r9Q2SzvU17XCDZun8";
    private final String BASE_API_URL = "https://exercise.trunarrative.cloud/TruProxyAPI/rest/Companies/v1";

    public ResponseEntity<ResponseDTO> searchCompany(RequestDTO requestDTO) throws JSONException {
        CompanyDTO[] companies;
        if (requestDTO.getCompanyNumber() != null ){
            companies = getStringResponseCompany(requestDTO.getCompanyNumber());
        }
        else {
            companies = getStringResponseCompany(requestDTO.getCompanyName());
        }
        for (CompanyDTO c : companies) {
            c.setOfficers(getStringResponseOfficer(c.getCompany_number()));
        }
        ResponseDTO response = new ResponseDTO();
        response.setTotal_results(companies.length);
        response.setItems(companies);
        return ResponseEntity.ok(response);
    }

    private CompanyDTO[] getStringResponseCompany(String nameOrNumber) throws JSONException {
        String url = BASE_API_URL + "/Search?Query=" + nameOrNumber;
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-api-key", API_KEY);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        JSONHelper jsonHelper = new JSONHelper();
        return jsonHelper.jsonResponseToCompanies(response.getBody());
    }

    private OfficerDTO[] getStringResponseOfficer(String companyNumber) throws JSONException {
        String url = BASE_API_URL + "/Officers?CompanyNumber=" + companyNumber;
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-api-key", API_KEY);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        JSONHelper jsonHelper = new JSONHelper();
        return jsonHelper.jsonResponseToOfficers(response.getBody());
    }
}
