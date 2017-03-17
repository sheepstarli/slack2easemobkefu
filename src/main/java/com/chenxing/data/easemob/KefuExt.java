package com.chenxing.data.easemob;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * KefuExt
 *
 * @author Chenxing Li
 * @date 24/02/2017 18:46
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KefuExt {

    private KefuVisitor visitor;

    public KefuExt() {
        super();
    }

    public KefuExt(KefuVisitor visitor) {
        super();
        this.visitor = visitor;
    }

}
