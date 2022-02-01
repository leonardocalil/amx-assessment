package com.amx.conding.test.assessment.controller;

import com.amx.conding.test.assessment.dto.PatientListDto;
import com.amx.conding.test.assessment.service.ConverterService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/converter")
public class ConverterController {


    private final ConverterService converterService;

    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }


    @PostMapping(value = "/xml-to-json",
            consumes = {MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity xmlToJson(@RequestBody String xml) {
        return ResponseEntity.ok(converterService.converterXmlToJson(xml));
    }

}
