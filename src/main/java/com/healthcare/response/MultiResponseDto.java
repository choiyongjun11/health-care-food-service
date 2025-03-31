package com.healthcare.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Access;
import java.util.List;

@Getter
@AllArgsConstructor
public class MultiResponseDto<T> {
    private List<T> data;



}
