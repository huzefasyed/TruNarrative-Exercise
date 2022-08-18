package com.TruNarrative.TruNarrativeSpringExercise.controller;
import com.TruNarrative.TruNarrativeSpringExercise.dto.RequestDTO;
import com.TruNarrative.TruNarrativeSpringExercise.dto.ResponseDTO;
import com.TruNarrative.TruNarrativeSpringExercise.service.SearchApiService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class SearchApiController {
    @Autowired
    SearchApiService searchApiService;

    @PostMapping(value = "/searchAPI",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ResponseDTO> searchCompany(@RequestBody RequestDTO requestDTO) throws JSONException {
        return this.searchApiService.searchCompany(requestDTO);
    }
}
