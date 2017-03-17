package com.chenxing.data.easemob;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = EasemobTxtMessageBody.class, name = "txt")
		})
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface EasemobMessageBody extends Serializable{
    String getType();
}
