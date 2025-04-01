package com.healthcare.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

@Getter
@AllArgsConstructor
public class PageInfo {
    private int page;
    private int size;
    private int totalPages;
}
