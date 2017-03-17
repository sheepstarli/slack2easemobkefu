package com.chenxing.data.easemob;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * IMCallback
 *
 * @author Chenxing Li
 * @date 01/03/2017 18:44
 */
@Data
public class IMCallback {
    private String callId;
    @JsonProperty("chat_type")
    private String chatType;
    private String eventType;
    private String from;
    @JsonProperty("msg_id")
    private String msgId;
    private String security;
    private String to;
    private Long timestamp;
    private EasemobPayload payload;
}
