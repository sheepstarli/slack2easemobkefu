package com.chenxing.data.easemob;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * KefuVisitor
 *
 * @author Chenxing Li
 * @date 24/02/2017 18:46
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KefuVisitor {
    private String userNickname;
    private String trueName;
    private Integer sex;
    private String qq;
    private String email;
    private String phone;
    private String companyName;
    private String description;

    public KefuVisitor() {
        super();
    }

    public KefuVisitor(String userNickname, String companyName) {
        super();
        this.userNickname = userNickname;
        this.companyName = companyName;
    }
}
