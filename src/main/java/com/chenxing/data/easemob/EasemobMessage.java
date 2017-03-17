package com.chenxing.data.easemob;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

/**
 * EasemobMessage
 *
 * @author Chenxing Li
 * @date 24/02/2017 17:47
 */
@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EasemobMessage<T> {

    private String from;
    private String targetType = "users";
    private List<String> target;
    private T msg;
    private EasemobExt ext;
}
