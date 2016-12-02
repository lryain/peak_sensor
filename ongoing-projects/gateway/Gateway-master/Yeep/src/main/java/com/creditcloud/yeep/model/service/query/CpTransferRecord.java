/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.yeep.model.service.query;

import com.creditcloud.model.BaseObject;
import com.creditcloud.yeep.enums.BizType;
import com.creditcloud.yeep.enums.Status;
import com.creditcloud.yeep.enums.UserType;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author tinglany
 */
@XmlType
public class CpTransferRecord extends BaseObject {

    @NotNull
    private String requestNo;
    
    @NotNull
    private BizType bizType;
    
    @NotNull
    private BigDecimal amount;
    
    @NotNull
    private Status status;
    
    @NotNull
    private Status subStatus;
    
    private UserType sourceUserType;
    
    private String sourceUserNo; 
    
    private String createdTime;
    
    private String completedTime;

    public CpTransferRecord() {
    }

    public CpTransferRecord(String requestNo, BizType bizType, BigDecimal amount, Status status, Status subStatus, UserType sourceUserType, String sourceUserNo, String createdTime, String completedTime) {
        this.requestNo = requestNo;
        this.bizType = bizType;
        this.amount = amount;
        this.status = status;
        this.subStatus = subStatus;
        this.sourceUserType = sourceUserType;
        this.sourceUserNo = sourceUserNo;
        this.createdTime = createdTime;
        this.completedTime = completedTime;
    }


    public String getRequestNo() {
        return requestNo;
    }

    public BizType getBizType() {
        return bizType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Status getStatus() {
        return status;
    }

    public Status getSubStatus() {
        return subStatus;
    }

    public UserType getSourceUserType() {
        return sourceUserType;
    }

    public String getSourceUserNo() {
        return sourceUserNo;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getCompletedTime() {
        return completedTime;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public void setBizType(BizType bizType) {
        this.bizType = bizType;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setSubStatus(Status subStatus) {
        this.subStatus = subStatus;
    }

    public void setSourceUserType(UserType sourceUserType) {
        this.sourceUserType = sourceUserType;
    }

    public void setSourceUserNo(String sourceUserNo) {
        this.sourceUserNo = sourceUserNo;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public void setCompletedTime(String completedTime) {
        this.completedTime = completedTime;
    }
    
    
}
