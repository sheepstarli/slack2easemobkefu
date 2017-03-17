package com.chenxing.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * SlackUser
 *
 * @author Chenxing Li
 * @date 23/02/2017 19:23
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SlackUser {
    private String id;
    private String teamId;
    private String name;
    private Boolean deleted;
    private String status;
    private String color;
    private String realName;
    private String tz;
    private String tzLabel;
    private Long tzOffset;
    private SlackUserProfile profile;
    private Boolean isAdmin;
    private Boolean isOwner;
    private Boolean isPrimaryOwner;
    private Boolean isRestricted;
    private Boolean isUltraRestricted;
    private Boolean isBot;
    private Boolean has_2fa;
}
