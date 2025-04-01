package com.healthcare.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SingleResponseDto<T> {
    private T data; //제한적인 type 만 반환하고자 제네릭을 사용했습니다.
}
