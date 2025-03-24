package com.healthcare.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//no.2
//공백 처리 기능 구현 - 다른 패키지 내에서도 쉽게 사용할 수 있게 @ 애너테이션 기반으로 구성했습니다.

@Target(ElementType.FIELD) //클래스의 필드에만 붙일 수 있는 어노테이션이다.
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NotSpaceValidator.class})
public @interface NotSpace {
    // 입력값이 공백이면 안되게 해주며 잘못된 공백이 들어오지 않게 하기 위해 사용자에게 메시지로 알려줍니다.
    // 어노테이션(annotation)에서 기본 메시지를 정의하는 부분입니다.
    String message() default "공백이 아니어야 합니다."; //유효성 검사(validation) 실패 이유를 설명합니다.
    Class<?>[] group() default {};
    Class<? extends Payload>[] payload() default{};

}
