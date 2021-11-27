package edu.unifacef.aluguelDeCarros.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ErrorResponseDTOBuilder {
    private final int code; // required
    private String defaultMessage; // required
    private List<Map<String, String>> fields = new ArrayList<Map<String, String>>();

    public ErrorResponseDTOBuilder(int code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public ErrorResponseDTOBuilder fields(List<Map<String, String>> fields){
        this.fields = fields;
        return this;
    }

    public ErrorResponseDTO build(){
        ErrorResponseDTO er = new ErrorResponseDTO();
        er.setCode(this.code);
        er.setDefaultMessage(this.defaultMessage);
        er.setFields(this.fields);

        return er;
    }
}
