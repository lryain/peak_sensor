/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.model.loan;

import com.creditcloud.model.BaseObject;
import com.creditcloud.model.enums.loan.RepayType;
import java.math.BigDecimal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.joda.time.LocalDate;

/**
 * 还款详情
 *
 * @author rooseek
 */
@XmlRootElement
public class Repayment extends BaseObject {

    private static final long serialVersionUID = 20130918L;

    @NotNull
    @XmlElement(name = "amountPrincipal")
    private BigDecimal amountPrincipal;

    @NotNull
    @XmlElement(name = "amountInterest")
    private BigDecimal amountInterest;

    @NotNull
    @XmlElement(name = "amountOutstanding")
    private BigDecimal amountOutstanding;

    @NotNull
    @XmlElement(name = "dueDate")
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
     * 当期的应还款总额.
     *
     * amountPrincipal + amountInterest
     *
     * @return
     */
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
