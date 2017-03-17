package com.chenxing.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(value= Include.NON_NULL)
public class ApiResponse {
	public static final String STATUS_OK = "OK";
	public static final String STATUS_FAIL = "FAIL";
	
	/**
	 * 标明请求是否成功
	 */
	private String status;
    /**
     * 错误编码
     */
    private String errorCode;
    /**
     * 错误信息,用作前端提示
     */
    private String errorDescription;
    /**
     * 存放多个返回结果,比如查询坐席列表的结果
     */
    private List<?> entities;
    /**
     * 存放单个返回结果,比如查询某个坐席的结果
     */
    private Object entity;
    

}
