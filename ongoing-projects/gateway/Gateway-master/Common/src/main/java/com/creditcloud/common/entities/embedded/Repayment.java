/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.common.entities.embedded;

import com.creditcloud.common.entities.BaseEntity;
import com.creditcloud.common.entities.utils.LocalDateConverter;
import com.creditcloud.model.enums.loan.RepayType;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.LocalDate;

/**
 *
 * @author rooseek
 */
@Embeddable
public class Repayment extends BaseEntity {

    /**
     * 偿还本金
     */
    @Min(0)
    @Column(nullable = false,
            precision = 15,
            scale = 2)
    private BigDecimal amountPrincipal;

    /**
     * 偿还利息
     */
    @Min(0)
    @Column(nullable = false,
            precision = 15,
            scale = 2)
    private BigDecimal amountInterest;

    /**
     * 剩余本息
     */
    @Min(0)
    @Column(nullable = false,
            precision = 15,
            scale = 2)
    private BigDecimal amountOutstanding;

    @Converter(name = "localDateConverter",
               converterClass = LocalDateConverter.class)
    @Convert("localDateConverter")
    @Column(nullable = false)
    private LocalDate dueDate;

    public Repayment() {
    }

    public Repayment(BigDecimal amountPrincipal,
                     BigDecimal amountInterest,
                     BigDecimal amountOutstanding,
                     LocalDate dueDate) {
        this.amountPrincipal = amountPrincipal;
        this.amountInterest = amountInterest;
        this.amountOutstanding = amountOutstanding;
        this.dueDate = dueDate;
    }

    public BigDecimal getAmountPrincipal() {
        return amountPrincipal;
    }

    public BigDecimal getAmountInterest() {
        return amountInterest;
    }

    public BigDecimal getAmountOutstanding() {
        return amountOutstanding;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setAmountPrincipal(BigDecimal amountPrincipal) {
        this.amountPrincipal = amountPrincipal;
    }

    public void setAmountInterest(BigDecimal amountInterest) {
        this.amountInterest = amountInterest;
    }

    public void setAmountOutstanding(BigDecimal amountOutstanding) {
        this.amountOutstanding = amountOutstanding;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * 获得当期的应还款总额.
     *
     * amountInterest + amountPrincipal
     *
     * @return
     */
    @Transient
    public BigDecimal getAmount() {
        return amountInterest.add(amountPrincipal);
    }

    public BigDecimal getAmount(RepayType type) {
        switch (type) {
            case Principal:
                return amountPrincipal;
            case PrincipalAndInterest:
                return getAmount();
            case Interest:
                return amountInterest;
            default:
                return BigDecimal.ZERO;
        }
    }

    public BigDecimal getPrincipal(RepayType type) {
        switch (type) {
            case Principal:
            case PrincipalAndInterest:
                return amountPrincipal;
            case Interest:
                return BigDecimal.ZERO;
            default:
                return BigDecimal.ZERO;
        }
    }

    public BigDecimal getInterest(RepayType type) {
        switch (type) {
            case Interest:
            case PrincipalAndInterest:
                return amountInterest;
            case Principal:
                return BigDecimal.ZERO;
            default:
                return BigDecimal.ZERO;
        }
    }
}
