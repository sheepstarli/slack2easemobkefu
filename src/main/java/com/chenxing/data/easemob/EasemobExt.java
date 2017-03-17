package com.chenxing.data.easemob;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * EasemobExt
 *
 * @author Chenxing Li
 * @date 24/02/2017 18:45
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EasemobExt {

    private KefuExt weichat;

    public EasemobExt() {
        super();
    }

    public EasemobExt(KefuExt weichat) {
        super();
        this.weichat = weichat;
    }

}
