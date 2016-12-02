package com.creditcloud.wealthproduct.model;

import com.creditcloud.model.BaseObject;
import com.creditcloud.wealthproduct.enums.SubscribeStatus;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 认购
 *
 * @author rooseek
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class Subscribe extends BaseObject {

    private static final long serialVersionUID = 20140924L;

    /**
     * ID
     * 
     * @return
     */
    private String id;

    /**
     * 产品ID
     * 
     * @return
     */
    @NotNull
    private String productId;

    /**
     * 用户ID
     * 
     * @return
     */
    @NotNull
    private String userId;

    /**
     * 认购金额
     * 
     * @return
     */
    @NotNull
    private BigDecimal amount;

    /**
     * 产品利率
     * 
     * @return
     */
    @NotNull
    private WealthProductRate rate;
    
    /**
     * 认购状态
     * 
     * @return
     */
    @NotNull
    private SubscribeStatus status;
    
    /**
     * 备注
     * 
     * @return
     */
    private String remark;
    
    /**
     * 额外信息
     * 
     * @return
     */
    private String priv;
    
    /**
     * 预约时间
     * 
     * @return
     */
    protected Date timeAppointed;
    
    /**
     * 打款时间
     * 
     * @return
     */
    protected Date timePaid;

}