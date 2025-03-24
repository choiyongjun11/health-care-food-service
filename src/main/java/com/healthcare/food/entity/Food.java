package com.healthcare.food.entity;

import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.Id;
import java.util.Date;

public class Food {
    @Id
    private long foodId;
    private String foodName;
    private int viewCount;
    private Date foodCreatedDate;

}
