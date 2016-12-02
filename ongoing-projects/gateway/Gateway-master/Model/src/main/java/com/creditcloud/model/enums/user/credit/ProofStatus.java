/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.model.enums.user.credit;

import com.creditcloud.model.enums.BaseEnum;

/**
 * 证明的状态
 *
 * @author rooseek
 */
public enum ProofStatus implements BaseEnum {

    UNCHECKED("未审核"),
    CHECKED("审核通过"),
    DENIED("审核未通过"),
    ARCHIVED("已存档"),
    DELETED("已刪除");

    private final String key;

    private ProofStatus(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
