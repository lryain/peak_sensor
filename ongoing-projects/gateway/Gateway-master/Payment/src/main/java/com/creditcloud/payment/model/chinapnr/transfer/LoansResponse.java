/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.payment.model.chinapnr.transfer;

import com.creditcloud.payment.model.chinapnr.base.BaseResponse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * 自动扣款(放款)
 *
 * @author rooseek
 */
@ToString(callSuper = true)
public class LoansResponse extends BaseResponse {

    @FormParam("OrdId")
    @NotNull
    @Size(max = 20)
    private String OrdId;

    @FormParam("OrdDate")
    @NotNull
    @Size(min = 8, max = 8)
    private String OrdDate;

    @FormParam("OutCustId")
    @NotNull
    @Size(max = 16)
    private String OutCustId;

    @FormParam("OutAcctId")
    @Size(max = 9)
    private String OutAcctId;

    @FormParam("TransAmt")
    @NotNull
    @Size(max = 14)
    private String TransAmt;

    @FormParam("Fee")
    @NotNull
    @Size(max = 12)
    private String Fee;

    @FormParam("InCustId")
    @NotNull
    @Size(max = 16)
    private String InCustId;

    @FormParam("InAcctId")
    @Size(max = 9)
    private String InAcctId;

    @FormParam("SubOrdId")
    @NotNull
    @Size(max = 20)
    private String SubOrdId;

    @FormParam("SubOrdDate")
    @NotNull
    @Size(min = 8, max = 8)
    private String SubOrdDate;

    @FormParam("IsDefault")
    @NotNull
    @Size(min = 1, max = 1)
    private String IsDefault;

    @FormParam("BgRetUrl")
    @NotNull
    @Size(max = 128)
    private String BgRetUrl;

    @FormParam("OpenBankId")
    @Size(max = 8)
    private String OpenBankId;

    @FormParam("OpenAcctId")
    @Size(max = 40)
    private String OpenAcctId;

    public LoansResponse() {
    }

    public String getOrdId() {
        return OrdId;
    }

    public String getOrdDate() {
        return OrdDate;
    }

    public String getOutCustId() {
        return OutCustId;
    }

    public String getOutAcctId() {
        return OutAcctId;
    }

    public String getTransAmt() {
        return TransAmt;
    }

    public String getFee() {
        return Fee;
    }

    public String getInCustId() {
        return InCustId;
    }

    public String getInAcctId() {
        return InAcctId;
    }

    public String getSubOrdId() {
        return SubOrdId;
    }

    public String getSubOrdDate() {
        return SubOrdDate;
    }

    public String getIsDefault() {
        return IsDefault;
    }

    public String getBgRetUrl() {
        return BgRetUrl;
    }

    public String getOpenBankId() {
        return OpenBankId;
    }

    public String getOpenAcctId() {
        return OpenAcctId;
    }

    public void setOrdId(String OrdId) {
        this.OrdId = OrdId;
    }

    public void setOrdDate(String OrdDate) {
        this.OrdDate = OrdDate;
    }

    public void setOutCustId(String OutCustId) {
        this.OutCustId = OutCustId;
    }

    public void setOutAcctId(String OutAcctId) {
        this.OutAcctId = OutAcctId;
    }

    public void setTransAmt(String TransAmt) {
        this.TransAmt = TransAmt;
    }

    public void setFee(String Fee) {
        this.Fee = Fee;
    }

    public void setInCustId(String InCustId) {
        this.InCustId = InCustId;
    }

    public void setInAcctId(String InAcctId) {
        this.InAcctId = InAcctId;
    }

    public void setSubOrdId(String SubOrdId) {
        this.SubOrdId = SubOrdId;
    }

    public void setSubOrdDate(String SubOrdDate) {
        this.SubOrdDate = SubOrdDate;
    }

    public void setIsDefault(String IsDefault) {
        this.IsDefault = IsDefault;
    }

    public void setBgRetUrl(String BgRetUrl) {
        this.BgRetUrl = BgRetUrl;
    }

    public void setOpenBankId(String OpenBankId) {
        this.OpenBankId = OpenBankId;
    }

    public void setOpenAcctId(String OpenAcctId) {
        this.OpenAcctId = OpenAcctId;
    }

    @Override
    public String chkString() {
        StringBuilder sb = new StringBuilder(baseChkString());
        sb.append(StringUtils.trimToEmpty(getOrdId()))
                .append(StringUtils.trimToEmpty(getOrdDate()))
                .append(StringUtils.trimToEmpty(getOutCustId()))
                .append(StringUtils.trimToEmpty(getOutAcctId()))
                .append(StringUtils.trimToEmpty(getTransAmt()))
                .append(StringUtils.trimToEmpty(getFee()))
                .append(StringUtils.trimToEmpty(getInCustId()))
                .append(StringUtils.trimToEmpty(getInAcctId()))
                .append(StringUtils.trimToEmpty(getSubOrdId()))
                .append(StringUtils.trimToEmpty(getSubOrdDate()))
                .append(StringUtils.trimToEmpty(getIsDefault()))
                .append(StringUtils.trimToEmpty(getBgRetUrl()))
                .append(StringUtils.trimToEmpty(getOpenBankId()))
                .append(StringUtils.trimToEmpty(getOpenAcctId()))
                .append(StringUtils.trimToEmpty(getMerPriv()));
        return sb.toString();
    }
}
