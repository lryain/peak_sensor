package com.creditcloud.wealthproduct.model;

import com.creditcloud.model.BaseObject;
import com.creditcloud.model.constraints.IncrementalInteger;
import com.creditcloud.wealthproduct.WealthProductConstant;
import com.creditcloud.wealthproduct.enums.IncomeType;
import com.creditcloud.wealthproduct.enums.InvestmentArea;
import com.creditcloud.wealthproduct.enums.ProductStatus;
import java.util.Collection;
import javax.enterprise.inject.Default;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * 理财产品
 * 
 * @author rooseek
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class WealthProduct extends BaseObject implements WealthProductConstant {

    /**
     * 产品ID
     * 
     * @return
     */
    private String id;
    
    /**
     * 产品代码 (其他形式编码)
     * 
     * @return
     */
    @FormParam("serial")
    @Size(max = MAX_SERIAL_LENGTH)
    private String serial;
    
    /**
     * 产品名称
     * 
     * @return
     */
    @FormParam("title")
    @Size(max = MAX_TITLE_LENGTH)
    @NotNull
    private String title;

    /**
     * 产品简称
     * 
     * @return
     */
    @FormParam("shortTitle")
    @Size(max = MAX_TITLE_LENGTH)
    private String shortTitle;
    
    /**
     * 显示名称
     * 
     * @return
     */
    @FormParam("displayTitle")
    @Size(max = MAX_TITLE_LENGTH)
    private String displayTitle;
    
    /**
     * 产品状态
     * 
     * @return
     */
    @FormParam("status")
    @NotNull
    private ProductStatus status;
    
    /**
     * 收益类型
     * 
     * @return
     */
    @FormParam("incomeType")
    @NotNull
    private IncomeType incomeType;
    
    /**
     * 投资领域
     * 
     * @return
     */
    private Collection<InvestmentArea> investmentArea;
    
    /**
     * 成立规模/募集金额
     * 
     * @return
     */
    @FormParam("amount")
    @IncrementalInteger(min = MIN_RAISE_AMOUNT,
                        increment = AMOUNT_INCREMENT,
                        max = MAX_RAISE_AMOUNT,
                        groups = Default.class)
    @NotNull
    private Integer amount;

    /**
     * 最小成立规模/最小募集金额
     * 
     * @return
     */
    @FormParam("minAmount")
    @IncrementalInteger(min = MIN_RAISE_AMOUNT,
                        increment = AMOUNT_INCREMENT,
                        max = MAX_RAISE_AMOUNT,
                        groups = Default.class)
    @NotNull
    private Integer minAmount;
    
    /**
     * 实际认购金额
     * 
     * @return
     */
    @FormParam("subscribeAmount")
    private Integer subscribeAmount;
    
    /**
     * 实际认购数
     * 
     * @return
     */
    @FormParam("subscribeNumber")
    private Integer subscribeNumber;
    
    /**
     * 产品周期
     * 
     * @return
     */
    private WealthProductSchedule schedule;

    /**
     * 创建人
     * 
     * @return
     */
    @FormParam("employeeId")
    private String employeeId;
 
    /**
     * 开放募集时长 单位：天
     * 
     * @return
     */
    @IncrementalInteger(min = MIN_TRANSFER_TIME_OUT,
                        increment = TIME_OUT_INCREMENT,
                        max = MAX_TRANSFER_TIME_OUT,
                        groups = Default.class)
    private Integer timeOut;
    
    /**
     * 打款时长 单位：天
     * 
     * @return
     */
    @IncrementalInteger(min = MIN_TIME_OUT,
                        increment = TIME_OUT_INCREMENT,
                        max = MAX_TIME_OUT,
                        groups = Default.class)
    private Integer transferTimeOut;
    
    /**
     * 成立日后多长时间后开始计息 单位：天
     * 
     * @return
     */
    @IncrementalInteger(min = MIN_TIME_OUT,
                        increment = TIME_OUT_INCREMENT,
                        max = MAX_TIME_OUT,
                        groups = Default.class)
    private Integer establishTimeOut;
    
    /**
     * 是否只能认购
     * 
     * @return
     */
    @NotNull
    private boolean subscribeOnly;
    
    /**
     * 归属类别 (WealthProductCategory->key)
     * 
     * @return
     */
    @FormParam("categoryKey")
    private String categoryKey;
    
    /**
     * 展示序号 （首页热门）
     * 
     * @return
     */
    private Integer ordinal;
    
    /**
     * 是否激活（显示是在前端）
     * 
     * @return
     */
    private boolean enable;
    
    /**
     * 是否按照排期 or 可能提前到期
     * 
     * @return
     */
    private boolean scheduleOnly;
    
    /**
     * 是否投资
     * 
     * @return
     */
    private WealthProductInvestRule investRule;
    
    /**
     * 相关信息
     * 
     * @return
     */
    private WealthProductInfo info;
     
    /**
     * 预期收益率
     */
    @FormParam("expectedRate")
    private BigDecimal expectedRate;
    /**
     * 是否预先生成还款计划，一般对于固定收益且线上统一结算的理财产品需要结算时预先生成，方便还款<p>
     */
//    private boolean generateRepayment;
    
    public WealthProduct(String id,
                         String serial,
                         String title,
                         String shortTitle,
                         String displayTitle,
                         ProductStatus status,
                         IncomeType incomeType,
                         Collection<InvestmentArea> investmentArea,
                         Integer amount,
                         Integer minAmount,
                         Integer subscribeAmount,
                         Integer subscribeNumber,
                         WealthProductSchedule schedule,
                         String employeeId,
                         Integer timeOut,
                         Integer transferTimeOut,
                         Integer establishTimeOut,
                         boolean subscribeOnly,
                         String categoryKey,
                         Integer ordinal,
                         boolean enable,
                         boolean scheduleOnly,
                         WealthProductInvestRule investRule,
                         WealthProductInfo info
    ) {
        this.id = id;
        this.serial = serial;
        this.title = title;
        this.shortTitle = shortTitle;
        this.displayTitle = displayTitle;
        this.status = status;
        this.incomeType = incomeType;
        this.investmentArea = investmentArea;
        this.amount = amount;
        this.minAmount = minAmount;
        this.subscribeAmount = subscribeAmount;
        this.subscribeNumber = subscribeNumber;
        this.schedule = schedule;
        this.employeeId = employeeId;
        this.timeOut = timeOut;
        this.transferTimeOut = transferTimeOut;
        this.establishTimeOut = establishTimeOut;
        this.subscribeOnly = subscribeOnly;
        this.categoryKey = categoryKey;
        this.ordinal = ordinal;
        this.investRule = investRule;
        this.info = info;
    }
}