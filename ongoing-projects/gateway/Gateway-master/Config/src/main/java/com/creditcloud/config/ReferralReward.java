/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.config;

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;

/**
 * config for referral reward
 *
 * @author rooseek
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class ReferralReward extends BaseConfig {

    private static final long serialVersionUID = 20140611L;

    /**
     * 注册天数,如果为0表示注册就有资格获得奖励
     */
    @Min(0)
    @Getter
    @XmlElement(required = false)
    private int registerDays = 0;

    /**
     * 投标金额,如果为0表示无需投资就有资格获得奖励
     */
    @Min(0)
    @Getter
    @XmlElement(required = false)
    private int investAmount = 0;

    /**
     * 奖励金额
     */
    @Min(0)
    @Getter
    @XmlElement(required = false)
    private int rewardAmount = 0;

}
