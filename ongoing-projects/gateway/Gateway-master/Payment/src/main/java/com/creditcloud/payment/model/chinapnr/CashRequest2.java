/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.payment.model.chinapnr;

import com.creditcloud.payment.model.PnRConstant;
import com.creditcloud.payment.model.chinapnr.constraint.PnRReturnURL;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.creditcloud.payment.model.chinapnr.base.BaseRequest;
import com.creditcloud.payment.model.chinapnr.enums.CmdIdType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * 取现，汇付2.0接口
 *
 * @author rooseek
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class CashRequest2 extends BaseRequest {

    @NotNull
    @Size(max = 20)
    private String OrdId;

    @NotNull
    @Size(max = 16)
    private String UsrCustId;

    @NotNull
    @Size(max = 14)
    private String TransAmt;

    @Size(max = 14)
    private String ServFee;

    @Size(max = 9)
    private String ServFeeAcctId;

    @Size(max = 40)
    private String OpenAcctId;

    @PnRReturnURL
    @Size(max = 128)
    private String RetUrl;

    @NotNull
    @PnRReturnURL
    @Size(max = 128)
    private String BgRetUrl;

    @Size(max = 128)
    private String Remark;

    private String CharSet;

    @Size(max = 512)
    private String ReqExt;

    /**
     * 用户商户指定取现手续费的收取对象,优先级别高</p>
     * 于商户配置的收取对象 M:向商户收取 U:向用户收取
     */
    @Size(min = 1, max = 1)
    private String FeeObjFlag;

    /**
     * FeeObjFlag=M.必填</p>
     * FeeObjFlag=U.忽略
     */
    @Size(max = 9)
    private String FeeAcctId;

    /**
     * FAST 快速取现</p>
     * GENERAL 一般取现</p>
     * IMMEDIATE 即时取现</p>
     * 可传的取现通道范围小于等于商户配置的取现通道
     */
    @Size(max = 9)
    private String CashChl;

    public CashRequest2(String MerCustId,
                        String OrdId,
                        String UsrCustId,
                        String TransAmt,
                        String OpenAcctId,
                        String RetUrl,
                        String BgRetUrl,
                        String Remark,
                        String ServeFee,
                        String ServFeeAcctId,
                        String ReqExt) {
        super(PnRConstant.Version_2, CmdIdType.Cash, MerCustId);
        this.OrdId = OrdId;
        this.UsrCustId = UsrCustId;
        this.TransAmt = TransAmt;
        this.OpenAcctId = OpenAcctId;
        this.RetUrl = RetUrl;
        this.BgRetUrl = BgRetUrl;
        this.Remark = Remark;
        this.CharSet = PnRConstant.CharSet;
        this.ServFee = ServeFee;
        this.ServFeeAcctId = ServFeeAcctId;
        this.ReqExt = ReqExt;
    }

    @Override
    public String chkString() {
        StringBuilder sb = new StringBuilder(baseChkString());
        sb.append(StringUtils.trimToEmpty(getOrdId()))
                .append(StringUtils.trimToEmpty(getUsrCustId()))
                .append(StringUtils.trimToEmpty(getTransAmt()))
                .append(StringUtils.trimToEmpty(getServFee()))
                .append(StringUtils.trimToEmpty(getServFeeAcctId()))
                .append(StringUtils.trimToEmpty(getOpenAcctId()))
                .append(StringUtils.trimToEmpty(getRetUrl()))
                .append(StringUtils.trimToEmpty(getBgRetUrl()))
                .append(StringUtils.trimToEmpty(getRemark()))
                .append(StringUtils.trimToEmpty(getMerPriv()))
                .append(StringUtils.trimToEmpty(getReqExt()));
        return sb.toString();
    }
}
