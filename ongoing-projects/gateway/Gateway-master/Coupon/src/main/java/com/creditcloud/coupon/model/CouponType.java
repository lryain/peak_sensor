/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.coupon.model;

import com.creditcloud.model.enums.BaseEnum;

/**
 * 
 * @author sobranie
 */
public enum CouponType implements BaseEnum {
    
    /**
     * 可以直接兑换成可用余额的奖券.
     * 
     */
    CASH("现金券"),
    /**
     * 投资固定收益产品时额外增加收益率的奖券.
     */
    INTEREST("加息券"),
    /**
     * 投资固定收益产品时，增加虚拟本金并获得对应利息收益的奖券.
     * 
     * 票面值为虚拟增加的本金，这部分不会参与到实际的资金借贷中，但根据这部分虚拟资金计算增值利息，作为奖励发放
     */
    PRINCIPAL("增值券"),
    /**
     * 投资返现券，确认特定投资后可以直接返现
     */
    REBATE("返现券");
    
    private final String key;
    
    private CouponType(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
    
}
