/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.payment.model.chinapnr.tender;

import com.creditcloud.payment.model.chinapnr.constraint.PnRReturnURL;
import com.creditcloud.payment.model.chinapnr.base.UserRequest;
import com.creditcloud.payment.model.chinapnr.enums.CmdIdType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;

/**
 * 自动投标计划
 *
 * @author rooseek
 */
public class AutoTenderPlanRequest extends UserRequest {

    @NotNull
    @Size(min = 1, max = 1)
    private String TenderPlanType;

    @Size(max = 14)
    private String TransAmt;

    @PnRReturnURL
    @Size(max = 128)
    private String RetUrl;

    public AutoTenderPlanRequest() {
    }

    public AutoTenderPlanRequest(String MerCustId,
                                 String tenderPlanType,
                                 String UsrCustId,
                                 String TransAmt,
                                 String RetUrl) {
        super(CmdIdType.AutoTenderPlan, MerCustId, UsrCustId);
        this.TenderPlanType = tenderPlanType;
        this.RetUrl = RetUrl;
        this.TransAmt = TransAmt;
    }

    public void setRetUrl(String RetUrl) {
        this.RetUrl = RetUrl;
    }

    public void setTransAmt(String TransAmt) {
        this.TransAmt = TransAmt;
    }

    public String getRetUrl() {
        return RetUrl;
    }

    public String getTransAmt() {
        return TransAmt;
    }

    public String getTenderPlanType() {
        return TenderPlanType;
    }

    public void setTenderPlanType(String TenderPlanType) {
        this.TenderPlanType = TenderPlanType;
    }

    @Override
    public String chkString() {
        StringBuilder sb = new StringBuilder(baseChkString()).
                append(StringUtils.trimToEmpty(getTenderPlanType())).
                append(StringUtils.trimToEmpty(getTransAmt())).
                append(StringUtils.trimToEmpty(getRetUrl())).
                append(StringUtils.trimToEmpty(getMerPriv()));
        return sb.toString();
    }
}
