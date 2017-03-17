package com.chenxing.data.easemob;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * EasmeobUser
 *
 * @author Chenxing Li
 * @date 24/02/2017 16:24
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EasemobUser {

    private String uuid;
    private String type;
    private Long created;
    private Long modified;
    private String username;
    private Boolean activated;
    private String nickname;

}
