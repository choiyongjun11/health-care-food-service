package com.healthcare.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

/*
no.1 - 공백(null) 예외 처리
1. ConstraintValidator 구현체 가져오기 - 구현체에 <NotSpace 애너테이션, String> 을 매개변수로 받습니다.

2. initialize, isValid 메서드 활용합니다.
    - 초기 메서드 양식
@Override
public void initialize(NotSpace constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
}

@Override
public boolean isValid(String value, ConstraintValidatorContext context) {
    return false;
}

 */
public class NotSpaceValidator implements ConstraintValidator<NotSpace, String> {
    @Override
    public void initialize(NotSpace constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || StringUtils.hasText(value);
    }

}
