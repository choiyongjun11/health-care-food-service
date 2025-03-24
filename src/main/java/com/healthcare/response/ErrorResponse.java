package com.healthcare.response;


import com.healthcare.exception.ExceptionCode;
import jakarta.validation.ConstraintViolation;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
//no.4
//code, message

@Getter
public class ErrorResponse {
    private int status;
    private String message;
    private List<FiledError> filedErrors;
    private List<ConstraintViolationError> violationErrors;

    private ErrorResponse(int status, String message) {
        this.status = status; //생성자
        this.message = message;
    }

    private ErrorResponse(final List<FiledError> filedErrors,
                          final List<ConstraintViolationError> violationErrors) {
        this.filedErrors = filedErrors;
        this.violationErrors = violationErrors;
    }

    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(FiledError.of(bindingResult), null);
    }

    public static ErrorResponse of(Set<ConstraintViolation<?>> violations) {
        return new ErrorResponse(null, ConstraintViolationError.of(violations));
    }

    public static ErrorResponse of(ExceptionCode exceptionCode) {
        return new ErrorResponse(exceptionCode.getCode(), exceptionCode.getMessage());
    }

    //HttpStatus enum 을 참고해보자.
    public static ErrorResponse of(HttpStatus httpStatus) {
        return new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase());
    }

    public static ErrorResponse of(HttpStatus httpStatus, String message) {
        return new ErrorResponse(httpStatus.value(), message);
    }

    @Getter
    public static class FiledError {
        private String field;
        private Object rejectedValue;
        private String reason;

        private FiledError(String field, Object rejectedValue, String reason) {
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }


        public static List<FiledError> of(BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();

            return fieldErrors.stream()
                    .map(error -> new FiledError( //주의사항: new FiledError 객체 생성 시 위에 선언된 private FiledError로 사용할 것. 이미 라이브러리에 구현된 FiledError 메서도가 있어 충돌이 난다.
                            error.getField(),
                            error.getRejectedValue() == null ?
                                    "": error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }

    }

    @Getter
    public static class ConstraintViolationError { //제약 위반 오류

        private String propertyPath;
        private Object rejectedValue;
        private String reason;

        private ConstraintViolationError(String propertyPath, Object rejectedValue, String reason) {
            this.propertyPath = propertyPath; //생성자
            this.rejectedValue = rejectedValue;
            this.reason = reason;
        }

        public static List<ConstraintViolationError> of( //ConstraintDescriptor 인터페이스 참고하여 메서드 확인하기.
                                                         Set<ConstraintViolation<?>> constraintViolations) {

            return constraintViolations.stream()
                    .map(constraintViolation -> new ConstraintViolationError(
                            constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getInvalidValue().toString(),
                            constraintViolation.getMessage()

                    )).collect(Collectors.toList());


        }


    }

}
