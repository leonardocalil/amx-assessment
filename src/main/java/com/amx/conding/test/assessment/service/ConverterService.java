package com.amx.conding.test.assessment.service;

import com.amx.conding.test.assessment.model.FieldMapping;
import com.amx.conding.test.assessment.model.FieldValueMapping;
import com.amx.conding.test.assessment.repository.FieldMappingRepository;
import com.amx.conding.test.assessment.util.EnumTypes;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


@Service
public class ConverterService {

    private final FieldMappingRepository fieldMappingRepository;
    private final int PRETTY_PRINT_INDENT_FACTOR = 4;
    private final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");


    public ConverterService(FieldMappingRepository fieldMappingRepository) {
        this.fieldMappingRepository = fieldMappingRepository;

    }

    public String converterXmlToJson(String xml) {

        JSONObject xmlJSONObj = XML.toJSONObject(xml);

        String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
        return replaceFieldAndValues(jsonPrettyPrintString);
    }

    private String replaceFieldAndValues(String jsonValue) {
        final StringBuilder result = new StringBuilder(jsonValue);
        Iterable<FieldMapping> all = fieldMappingRepository.findAll();
        all.forEach(field -> {
            int startIndexField = result.indexOf("\"" + field.getXmlName() + "\"");

            while (startIndexField > -1) {
                if (!CollectionUtils.isEmpty(field.getFieldValuesMapping())) {
                    replaceValue(startIndexField, result, field.getFieldValuesMapping());
                }
                if (!field.getXmlName().equals(field.getJsonName())) {
                    int endIndexField = result.indexOf(":", startIndexField);
                    result.replace(startIndexField, endIndexField, "\"" + field.getJsonName() + "\"");
                }
                startIndexField = result.indexOf("\"" + field.getXmlName() + "\"", startIndexField + 1);
            }
        });
        return result.toString();
    }

    private StringBuilder replaceValue(Integer indexField, StringBuilder jsonValue, List<FieldValueMapping> fieldValuesMapping) {
        Integer startIndexValue = jsonValue.indexOf(":", indexField) + 1;
        Integer endIndexValue = jsonValue.indexOf(",", startIndexValue);
        if (endIndexValue < 0) {
            endIndexValue = jsonValue.indexOf("}", startIndexValue);
        }
        String value = jsonValue.substring(startIndexValue, endIndexValue);
        if (value.contains("}")) {
            value = value.replace("}", "").trim();
            endIndexValue = startIndexValue + value.length() + 1;
        }
        boolean containDoubleQuote = value.contains("\"");
        value = value.replaceAll("\"", "").trim();
        if (!StringUtils.hasLength(value)) {
            return jsonValue;
        }

        for (FieldValueMapping valueMapping : fieldValuesMapping) {
            if (EnumTypes.DATE.equals(valueMapping.getXmlType())
                    && !EnumTypes.DATE.equals(valueMapping.getJsonType())) {
                calculateAndReplaceDateValue(startIndexValue, endIndexValue, jsonValue, value);
                continue;
            }
            if (StringUtils.hasLength(valueMapping.getXmlValue())
                    && value.equals(valueMapping.getXmlValue())) {
                String finalValue = valueMapping.getJsonValue();
                if (containDoubleQuote) {
                    finalValue = "\"" + finalValue + "\"";
                }
                jsonValue.replace(startIndexValue, endIndexValue, finalValue);
            }
        }
        return jsonValue;
    }

    private void calculateAndReplaceDateValue(Integer startIndex,
                                              Integer endIndex,
                                              StringBuilder jsonValue,
                                              String value) {
        Calendar dateValue = Calendar.getInstance();
        Calendar currentDate = Calendar.getInstance();


        try {
            dateValue.setTime(sdf.parse(value));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LocalDate dob = new LocalDate(dateValue.get(Calendar.YEAR), dateValue.get(Calendar.MONTH), dateValue.get(Calendar.DAY_OF_MONTH));
        LocalDate now = new LocalDate(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));

        int years = Years.yearsBetween(dob, now).getYears();

        jsonValue.replace(startIndex, endIndex, String.valueOf(years));

    }

}
