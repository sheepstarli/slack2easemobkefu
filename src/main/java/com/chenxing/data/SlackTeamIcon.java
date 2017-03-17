package com.chenxing.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * SlackTeamIcon
 *
 * @author Chenxing Li
 * @date 23/02/2017 19:10
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SlackTeamIcon {

    private String image_34;
    private String image_44;
    private String image_68;
    private String image_88;
    private String image_102;
    private String image_132;
    private String image_230;
    private Boolean imageDefault;

}
