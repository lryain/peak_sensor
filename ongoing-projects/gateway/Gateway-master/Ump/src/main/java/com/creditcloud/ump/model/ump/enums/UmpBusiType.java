/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.creditcloud.ump.model.ump.enums;

import com.creditcloud.model.enums.BaseEnum;
import com.creditcloud.model.util.Enums;

/**
 *
 * @author kdliu
 */
public enum UmpBusiType implements BaseEnum {
    RECHARGE("01", "充值"), 
    WITHDRAWL("02", "提现"),
    TENDER_TRANSFER("03", "标的转账"),
    TRANSFER("04", "转账");
    
    private String key;
    private String value;
    
    UmpBusiType(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    @Override
    public String getKey() {
        return key;
    }
    
    public String getValue() {
        return value;
    }
    
    public static UmpBusiType getEnum(String key) {
        return Enums.getEnumByKey(UmpBusiType.class, key);
    }
}
