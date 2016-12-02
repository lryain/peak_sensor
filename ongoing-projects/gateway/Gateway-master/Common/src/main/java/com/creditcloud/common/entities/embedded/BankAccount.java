/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.common.entities.embedded;

import com.creditcloud.common.entities.BaseEntity;
import com.creditcloud.model.constraints.BankAccountNumber;
import com.creditcloud.model.constraints.MobileNumber;
import com.creditcloud.model.constraints.RealName;
import com.creditcloud.model.enums.misc.Bank;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 银行账号
 *
 * @author rooseek
 */
@AllArgsConstructor
@Embeddable
public class BankAccount extends BaseEntity {

    /**
     * 开户人
     */
    @Getter
    @Setter
    @RealName
    @Column(nullable = false)
    private String name;

    /**
     * 开户银行:中国工商银行(ICBC)
     */
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Bank bank;

    /**
     * 开户行所在地:北京市西城区长安街12号
     */
    @Getter
    @Setter
    @Column(nullable = true)
    private String location;

    /**
     * 开户支行:工行北京分行海淀支行
     */
    @Getter
    @Setter
    @Column(nullable = true)
    private String branch;

    /**
     * 账号
     */
    @Getter
    @Setter
    @BankAccountNumber
    @Column(nullable = false)
    private String account;

    /**
     * 账号所在省份
     * 
     * @return
     */
    @Getter
    @Setter
    @Column(nullable = true)
    private String province;
    
    /**
     * 账号所在城市
     * 
     * @return
     */
    @Getter
    @Setter
    @Column(nullable = true)
    private String city;
    
    /**
     * 银行预留手机号
     */
    @Getter
    @Setter
    @MobileNumber
    @Column(nullable = true)
    private String bankMobile;
    
    public BankAccount() {
    }

    public BankAccount(String name,
                       Bank bank,
                       String location,
                       String branch,
                       String account) {
        this.name = name;
        this.bank = bank;
        this.location = location;
        this.branch = branch;
        this.account = account;
    }

}
