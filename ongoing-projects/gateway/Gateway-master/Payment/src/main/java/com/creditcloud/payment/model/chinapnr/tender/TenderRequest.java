/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.payment.model.chinapnr.tender;

import com.creditcloud.payment.model.chinapnr.constraint.PnRReturnURL;
import com.creditcloud.payment.model.PnRConstant;
import com.creditcloud.payment.model.chinapnr.base.BaseRequest;
import com.creditcloud.payment.model.chinapnr.constraint.PnRDate;
import com.creditcloud.payment.model.chinapnr.enums.CmdIdType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 主动/自动投标request
 *
 * @author rooseek
 */
@Data
@NoArgsConstructor
public class TenderRequest extends BaseRequest {

    @NotNull
    @Size(max = 20)
    private String OrdId;

    @NotNull
    @PnRDate
    private String OrdDate;

    @NotNull
    @Size(max = 14)
    private String TransAmt;

    @NotNull
    @Size(max = 16)
    private String UsrCustId;

    @NotNull
    @Size(max = 6)
    private String MaxTenderRate;

    @NotNull
    private String BorrowerDetails;

    @PnRReturnURL
    @Size(max = 128)
    private String RetUrl;

    @NotNull
    @PnRReturnURL
    @Size(max = 128)
    private String BgRetUrl;

    public TenderRequest(CmdIdType CmdId,
                         String MerCustId,
                         String OrdId,
                         String OrdDate,
                         String TransAmt,
                         String UsrCustId,
                         String MaxTenderRate,
                         String BorrowerDetails,
                         String RetUrl,
                         String BgRetUrl) {
        super(PnRConstant.Version, CmdId, MerCustId);
        this.OrdId = OrdId;
        this.OrdDate = OrdDate;
        this.TransAmt = TransAmt;
        this.UsrCustId = UsrCustId;
        this.MaxTenderRate = MaxTenderRate;
        this.BorrowerDetails = BorrowerDetails;
        this.RetUrl = RetUrl;
        this.BgRetUrl = BgRetUrl;
    }


    @Override
    public String chkString() {
        StringBuilder sb = new StringBuilder(baseChkString());
        sb.append(StringUtils.trimToEmpty(getOrdId()))
                .append(StringUtils.trimToEmpty(getOrdDate()))
                .append(StringUtils.trimToEmpty(getTransAmt()))
                .append(StringUtils.trimToEmpty(getUsrCustId()))
                .append(StringUtils.trimToEmpty(getMaxTenderRate()))
                .append(StringUtils.trimToEmpty(getBorrowerDetails()))
                .append(StringUtils.trimToEmpty(getRetUrl()))
                .append(StringUtils.trimToEmpty(getBgRetUrl()))
                .append(StringUtils.trimToEmpty(getMerPriv()));
        return sb.toString();
    }
}
