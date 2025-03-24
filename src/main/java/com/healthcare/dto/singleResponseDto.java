package com.healthcare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class singleResponseDto<T> {
    private T data;
}
