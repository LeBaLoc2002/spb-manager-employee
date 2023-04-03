package com.example.managerEmployees.appUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.text.Normalizer;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component
public class AppUtils {
    static final String stringAB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public final String DOMAIN_SERVER = "localhost";
    public final long TOKEN_MAX_AGE = 1000 * 60 * 60 * 24;

    private static final String DATE_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";

    public ResponseEntity<?> mapErrorToResponse(BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }






    public String replaceNonEnglishChar(String str) {
        str = str.toLowerCase();
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("đ", "d");
        str = str.replaceAll("[^\\x00-\\x7F]", "");

        return str;
    }

    public String removeNonAlphanumeric(String str) {
        do {
            str = str.replace(" ","-");
            str = str.replaceAll("[^a-zA-Z0-9\\-]", "-");
            str = str.replaceAll("--", "-");

            while (str.charAt(0) == '-') {
                str = str.substring(1);
            }

            while (str.charAt(str.length() - 1) == '-') {
                str = str.substring(0, str.length() - 1);
            }
        }
        while (str.contains("--"));

        return str.trim().toLowerCase(Locale.ENGLISH);
    }

}
