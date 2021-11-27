package edu.unifacef.aluguelDeCarros.exception;

import edu.unifacef.aluguelDeCarros.dto.ErrorResponseDTO;
import edu.unifacef.aluguelDeCarros.dto.ErrorResponseDTOBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json;charset=UTF-8";

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private HttpEntity<ErrorResponseDTO> handleValidationException(final HttpServletRequest request, final MethodArgumentNotValidException ex){
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, APPLICATION_JSON);

        List<Map<String, String>> errors = ex.getFieldErrors().stream().map(e -> {
            Map<String, String> map = new HashMap<String, String>();
            map.put(e.getField(), e.getDefaultMessage());
            return map;
        }).collect(Collectors.toList());

        String message = this.getMessage("error.validation", request);

        ErrorResponseDTO e = new ErrorResponseDTOBuilder(100, message)
                                .fields(errors)
                                .build();

        return new ResponseEntity<ErrorResponseDTO>(e, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DocumentNotFound.class)
    private HttpEntity<ErrorResponseDTO> handleValidationException(final HttpServletRequest request, final DocumentNotFound ex){
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, APPLICATION_JSON);
        ErrorResponseDTO e = new ErrorResponseDTOBuilder(001, ex.getMessage())
                .build();
        return new ResponseEntity<ErrorResponseDTO>(e, headers, HttpStatus.NOT_FOUND);
    }


    private String getMessage(String code, HttpServletRequest request, Object[] args) {
        try {
            return this.messageSource.getMessage(code, args, request.getLocale());
        } catch (NoSuchMessageException e){
            return this.messageSource.getMessage("error.defaultMessage", args, request.getLocale());
        } catch (Exception e) {
            return null;
        }
    }

    private String getMessage(String code, HttpServletRequest request) {
        try {
            return this.messageSource.getMessage(code, null, request.getLocale());
        } catch (NoSuchMessageException e){
            return this.messageSource.getMessage("error.defaultMessage", null, request.getLocale());
        } catch (Exception e) {
            return null;
        }
    }
}
