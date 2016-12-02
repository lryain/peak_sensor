/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.model.loan;

import com.creditcloud.model.BaseObject;
import com.creditcloud.model.constant.LoanConstant;
import com.creditcloud.model.constraints.IncrementalInteger;
import com.creditcloud.model.enums.Source;
import com.creditcloud.model.enums.loan.AutoAble;
import com.creditcloud.model.enums.loan.LoanPurpose;
import com.creditcloud.model.enums.loan.LoanRequestStatus;
import com.creditcloud.model.enums.loan.MortgageType;
import com.creditcloud.model.enums.loan.RepaymentMethod;
import com.creditcloud.model.misc.RealmEntity;
import com.creditcloud.model.user.User;
import java.util.Collection;
import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.creditcloud.model.user.corporation.CorporationUser;
import com.creditcloud.model.enums.loan.GuarantyStyle;

/**
 *
 * @author sobranie
 */
@XmlRootElement
@Data
@NoArgsConstructor
public class LoanRequest extends BaseObject {

    private static final long serialVersionUID = 20130917L;

    /**
     * LoanRequest Id
     */
    @XmlElement(name = "id")
    private String id;

    @XmlElement(name = "userId")
    private String userId;

    /**
     * 个人用户
     */
    @XmlElement(name = "user")
    private User user;

    /**
     * 企业负责人: 用于借款申请（默认为null） corporation.id = user.id
     */
    @XmlElement(name = "corporation")
    private CorporationUser corporation;

    /**
     * title
     */
    @NotNull
    @Size(min = 4, max = 60)
    @XmlElement(name = "title")

    private String title;

    /**
     * 目的
     */
    @NotNull
    @XmlElement(name = "purpose")
    private LoanPurpose purpose;

    /**
     * 金额
     */
    @NotNull
    @IncrementalInteger(min = LoanConstant.MIN_LOAN_AMOUNT,
                        increment = LoanConstant.LOAN_AMOUNT_INCREMENT,
                        max = LoanConstant.MAX_LOAN_AMOUNT,
                        groups = Default.class)
    @XmlElement(name = "amount")
    private int amount;

    /**
     * 期限
     */
    @NotNull
    @Valid
    @XmlElement(name = "duration")
    private Duration duration;

    /**
     * 年化利率（万分之几）
     */
    @NotNull
    @IncrementalInteger(min = LoanConstant.MIN_LOAN_RATE,
                        increment = 1,
                        max = LoanConstant.MAX_LOAN_RATE,
                        groups = Default.class)
    @XmlElement(name = "rate")
    private int rate;

    /**
     * 偿还方法
     */
    @NotNull
    @XmlElement(name = "method")
    private RepaymentMethod method;
    
    /**
     * 还款详细规则.
     * 
     * null表示默认
     */
    @Valid
    private RepaymentRule repaymentRule;

    /**
     * 描述
     */
    @Size(max = LoanConstant.MAX_LOAN_DESCRIPTION)
    @XmlElement(name = "description")
    private String description;

    /**
     * 状态(初始状态为：UNASSIGNED)
     */
    @NotNull
    @XmlElement(name = "status")
    private LoanRequestStatus status;

    /**
     * 提交时间
     */
    @NotNull
    @XmlElement(name = "timeSubmit")
    private Date timeSubmit;

    @XmlElement(name = "mortgaged")
    private boolean mortgaged;

    @XmlElement(name = "source")
    private Source source;

    @XmlElement(name = "employeeId")
    private String employeeId;

    private Collection<MortgageType> mortgageType;

    /**
     * 抵质押情况说明
     */
    @Size(max = LoanConstant.MAX_MORTGAGE_INFO)
    private String mortgageInfo;

    /**
     * 担保实体，可以为用户个人、商户、企业
     */
    private RealmEntity guaranteeEntity;

    /**
     * 担保情况说明
     */
    @Size(max = LoanConstant.MAX_GUARANTEE_INFO)
    private String guaranteeInfo;

    /**
     * 担保方式
     */
    private GuarantyStyle guarantyStyle;

    /**
     * 风险信息及措施
     */
    @Size(max = LoanConstant.MAX_RISK_INFO)
    private String riskInfo;

    /**
     * 商户提供的唯一编号,可以为null
     */
    @Size(max = LoanConstant.MAX_LOAN_REQUEST_SERIAL)
    private String serial;

    /**
     * 投资的限额,如果为Null则表示使用默认的限额
     */
    @Valid
    private InvestRule investRule;
    
    @Size(max = LoanConstant.MAX_LOAN_DESCRIPTION)
    @XmlElement(name = "reviewComment")
    private String reviewComment;

    /**
     * 商户平台自定义如json string
     */
    @Size(max = LoanConstant.MAX_LOAN_DESCRIPTION)
    private String clientPriv;

    /**
     * 项目提供方
     */
    private RealmEntity requestProvider;

    /**
     * 归属产品
     */
    private String productId;

    private String productKey;

    /**
     * 归属产品描述
     */
    private String productDescription;
    /**
     * 控制标的是否对所有人可见
     */
    private boolean hidden;
    
    /**
     * 起息日
     */
    private Date valueDate;
    
    /**
     * 关联的借款申请
     */
    private String parentId;
    
    /**
     * 用于页面显示的借款人id，默认为null<p>
     * 例如用于影子标之类业务
     */
    private String displayUserId;
    
    /**
     * 单纯的借款申请,不会产生后续标的等</p>
     * 对于某些业务例如配资申请
     */
    private boolean pureRequest;

    /**
     * 是否自动标
     */
    private AutoAble autoAble;
    
    /**
     *
     * @param id          LoanRequest Id, nullable
     * @param user        用户
     * @param title       title
     * @param purpose     目的
     * @param amount      金额
     * @param duration    期限
     * @param rate        年化利率（万分之几）
     * @param method      偿还方法
     * @param description 描述
     * @param status      状态(初始状态为：UNASSIGNED)
     * @param timeSubmit  提交时间
     */
    public LoanRequest(String id,
                       User user,
                       String title,
                       LoanPurpose purpose,
                       int amount,
                       Duration duration,
                       int rate,
                       RepaymentMethod method,
                       String description,
                       LoanRequestStatus status,
                       Date timeSubmit,
                       boolean mortgaged,
                       Source source,
                       String employeeId,
                       Collection<MortgageType> mortgageType,
                       String mortgageInfo,
                       RealmEntity guaranteeEntity,
                       String guaranteeInfo,
                       String riskInfo,
                       String serial,
                       InvestRule investRule) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.purpose = purpose;
        this.amount = amount;
        this.duration = duration;
        this.rate = rate;
        this.method = method;
        this.description = description;
        this.status = status;
        this.timeSubmit = timeSubmit;
        this.mortgaged = mortgaged;
        this.source = source;
        this.employeeId = employeeId;
        this.mortgageType = mortgageType;
        this.mortgageInfo = mortgageInfo;
        this.guaranteeEntity = guaranteeEntity;
        this.guaranteeInfo = guaranteeInfo;
        this.riskInfo = riskInfo;
        this.serial = serial;
        this.investRule = investRule;
    }

    public String getUserId() {
        if (userId == null) {
            return user == null ? null : user.getId();
        }
        return userId;
    }
    
    /**
     * 使用默认值
     * 
     * @return 
     */
    public RepaymentRule getRepaymentRule() {
        return repaymentRule == null ? RepaymentRule.DEFAULT : repaymentRule;
    }
}
