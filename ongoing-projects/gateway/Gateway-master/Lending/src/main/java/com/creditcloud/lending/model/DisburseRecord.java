/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.lending.model;

import com.creditcloud.lending.enums.DisburseStatus;
import com.creditcloud.model.BaseObject;
import com.creditcloud.model.enums.loan.DisburseType;
import com.creditcloud.model.misc.RealmEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author rooseek
 */
@Data
@NoArgsConstructor
public class DisburseRecord extends BaseObject {

    private static final long serialVersionUID = 20141110L;

    private String id;

    private String loanRepaymentId;

    /**
     * 执行垫付操作员工或者担保企业</p>
     * TODO use RealmEntity
     */
    @NotNull
    private String employeeId;

    @NotNull
    private DisburseStatus status;

    private DisburseType type;

    private String description;

    /**
     * 垫付来源，平台或者担保户等
     */
    private RealmEntity disburseSource;

    private Date timeRecorded;

    /**
     * 垫付金额
     */
    private BigDecimal disburseAmount;
    
    /**
     * 平台垫付后跟借款人收取的逾期费总和
     */
    @Transient
    private BigDecimal overdueFee = BigDecimal.ZERO;

    /**
     * 平台垫付后跟借款人收取的管理费总和
     */
    @Transient
    private BigDecimal manageFee = BigDecimal.ZERO;

    /**
     * 借款人登录名
     */
    @Transient
    private String loginName;

    public DisburseRecord(String id,
                          String loanRepaymentId,
                          String employeeId,
                          DisburseStatus status,
                          DisburseType type,
                          RealmEntity disburseSource,
                          String description) {
        this.id = id;
        this.loanRepaymentId = loanRepaymentId;
        this.employeeId = employeeId;
        this.status = status;
        this.type = type;
        this.disburseSource = disburseSource;
        this.description = description;
    }
    
    public DisburseRecord(String id,
                          String loanRepaymentId,
                          String employeeId,
                          DisburseStatus status,
                          DisburseType type,
                          RealmEntity disburseSource,
                          String description,
                          BigDecimal disburseAmount) {
        this.id = id;
        this.loanRepaymentId = loanRepaymentId;
        this.employeeId = employeeId;
        this.status = status;
        this.type = type;
        this.disburseSource = disburseSource;
        this.description = description;
        this.disburseAmount = disburseAmount;
    }

    /**
     * 平台垫付后跟借款人收取费用总和
     *
     * @return
     */
    public BigDecimal getTotalFee() {
        return overdueFee.add(manageFee);
    }

    public String getLoanTitle() {
        throw new UnsupportedOperationException();
    }

    public String getLoanSerial() {
        throw new UnsupportedOperationException();
    }
}
