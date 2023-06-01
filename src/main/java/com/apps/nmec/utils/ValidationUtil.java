package com.apps.nmec.utils;

import com.apps.nmec.exceptionhandlers.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Component
public class ValidationUtil {
    public static void validateEmptyCollection(Set set) {
        if (CollectionUtils.isEmpty(set)) {
            throw new CustomException().builder().errorMessage("Please provide roles.").httpStatus(HttpStatus.BAD_REQUEST).build();
        }
    }
}
