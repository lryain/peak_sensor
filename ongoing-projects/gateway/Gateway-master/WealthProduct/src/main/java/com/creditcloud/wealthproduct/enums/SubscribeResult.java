/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.wealthproduct.enums;

import com.creditcloud.model.enums.BaseEnum;

/**
 *
 * @author rooseek
 */
public enum SubscribeResult implements BaseEnum {

    SUCCESSFUL("认购成功"),
    EXCEED_TIMES_LIMIT("认购次数超过上限"),
    NO_BALANCE("认购已满"),
    FAIL("认购失败");

    private final String key;

    private SubscribeResult(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
