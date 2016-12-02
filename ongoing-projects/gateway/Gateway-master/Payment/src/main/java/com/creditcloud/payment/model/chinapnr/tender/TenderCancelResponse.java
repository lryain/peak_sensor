/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.payment.model.chinapnr.tender;

import com.creditcloud.payment.model.chinapnr.constraint.PnRReturnURL;
import com.creditcloud.payment.model.chinapnr.base.BaseResponse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * 投标撤销response
 *
 * @author rooseek
 */
@ToString(callSuper = true)
public class TenderCancelResponse extends BaseResponse {

    @FormParam("OrdId")
    @NotNull
    @Size(max = 20)
    private String OrdId;

    @FormParam("OrdDate")
    @NotNull
    @Size(min = 8, max = 8)
    private String OrdDate;

    @FormParam("TransAmt")
    @NotNull
    @Size(max = 14)
    private String TransAmt;

    @FormParam("UsrCustId")
    @NotNull
    @Size(max = 16)
    private String UsrCustId;

    @FormParam("RetUrl")
    @PnRReturnURL
    @Size(max = 128)
    private String RetUrl;

    @FormParam("BgRetUrl")
    @NotNull
    @PnRReturnURL
    @Size(max = 128)
    private String BgRetUrl;

    public TenderCancelResponse() {
    }

    public String getOrdId() {
        return OrdId;
    }

    public String getOrdDate() {
        return OrdDate;
    }

    public String getTransAmt() {
        return TransAmt;
    }

    public String getUsrCustId() {
        return UsrCustId;
    }

    public String getRetUrl() {
        return RetUrl;
    }

    public String getBgRetUrl() {
        return BgRetUrl;
    }

    public void setOrdId(String OrdId) {
        this.OrdId = OrdId;
    }

    public void setOrdDate(String OrdDate) {
        this.OrdDate = OrdDate;
    }

    public void setTransAmt(String TransAmt) {
        this.TransAmt = TransAmt;
    }

    public void setUsrCustId(String UsrCustId) {
        this.UsrCustId = UsrCustId;
    }

    public void setRetUrl(String RetUrl) {
        this.RetUrl = RetUrl;
    }

    public void setBgRetUrl(String BgRetUrl) {
        this.BgRetUrl = BgRetUrl;
    }

    @Override
    public String chkString() {
        StringBuilder sb = new StringBuilder(baseChkString());
        sb.append(StringUtils.trimToEmpty(getOrdId()))
                .append(StringUtils.trimToEmpty(getOrdDate()))
                .append(StringUtils.trimToEmpty(getTransAmt()))
                .append(StringUtils.trimToEmpty(getUsrCustId()))
                .append(StringUtils.trimToEmpty(getRetUrl()))
                .append(StringUtils.trimToEmpty(getBgRetUrl()))
                .append(StringUtils.trimToEmpty(getMerPriv()));
        return sb.toString();
    }
}
