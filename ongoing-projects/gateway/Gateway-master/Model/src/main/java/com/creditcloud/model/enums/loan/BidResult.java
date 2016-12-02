/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.model.enums.loan;

import com.creditcloud.model.enums.BaseEnum;

/**
 *
 * @author rooseek
 */
public enum BidResult implements BaseEnum {

    /**
     * 只表示投标的前置条件都满足,可以投标，并不代表最终投标能否成功
     */
    PRECONDITION_MEET("满足投标前置条件"),
    SUCCESSFUL("投标成功，资金被冻结"),
    PARTLY_SUCCESSFUL("投资成功,资金被冻结"),//但可贷款余额不足,只能满足部分投资
    BID_NOT_OPEN("贷款标没有开始,或者已经结束"),
    BID_NO_BALANCE("已满标"),
    BID_EXCEED_TIMES_LIMIT("投标次数超过上限"),
    BID_EXCEED_TOTAL_AMOUNT_LIMIT("投标总金额超过上限"),
    BID_EXCEED_SINGLE_AMOUNT_LIMIT("投标单次金额超过上限"),
    /**
     * 收到重复的bid请求
     */
    BID_REDUNDANT("重复投标"),
    USER_BALANCE_INSUFFICIENT("账户可用余额不足"),
    FROZEN_FAILED("冻结用户账户余额失败"),
    INVALID_AMOUNT("投资金额不合规，请查看产品说明"),
    SELF_BID_FORBIDDEN("不能投给自己"),
    BID_FORBIDDEN("不满足投标条件"),
    FAILED("其他原因失败"),
    /**
     * 投标金额对应的奖励找不到
     */
    REWARD_NOT_FOUND("没有投标金额对应的奖励"),
    REWARD_EXCEED_LIMIT("超过奖励限额");

    private final String key;

    private BidResult(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
