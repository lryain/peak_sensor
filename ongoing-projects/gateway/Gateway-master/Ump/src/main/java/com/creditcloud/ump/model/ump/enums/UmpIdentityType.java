/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.creditcloud.ump.model.ump.enums;

import com.creditcloud.model.enums.BaseEnum;
import com.creditcloud.model.util.Enums;

/**
 * UMP的证件类型
 * 
 * @author kdliu
 */
public enum UmpIdentityType implements BaseEnum{
    IDENTITY_CARD("身份证");
    
    private final String key;
    
    UmpIdentityType(String key) {
        this.key = key;
    }
    
    public static UmpIdentityType getEnum(String key) {
        return Enums.getEnumByKey(UmpIdentityType.class, key);
    }

    public String getKey() {
        return key;
    }

}
