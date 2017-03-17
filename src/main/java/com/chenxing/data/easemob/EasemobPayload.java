package com.chenxing.data.easemob;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * EasemobPayload
 *
 * @author Chenxing Li
 * @date 01/03/2017 19:20
 */
@Data
public class EasemobPayload {

    private List<EasemobMessageBody> bodies;
    private Map<String, Object> ext;

}
