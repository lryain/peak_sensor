/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.payment.model.chinapnr.base;

import com.creditcloud.model.BaseObject;
import com.creditcloud.payment.model.PnRConstant;
import com.creditcloud.payment.model.chinapnr.enums.CmdIdType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import org.apache.commons.lang3.StringUtils;

/**
 * 返回结果基类
 */
public abstract class BaseResponse extends BaseObject {

    @FormParam("CmdId")
    @NotNull
    private CmdIdType CmdId;

    @FormParam("RespCode")
    @NotNull
    private String RespCode;

    @FormParam("RespDesc")
    @NotNull
    private String RespDesc;

    @FormParam("MerCustId")
    @NotNull
    @Size(max = 16)
    private String MerCustId;
    
    @FormParam("MerPriv")
    @Size(max = 120)
    private String MerPriv;

    @FormParam("ChkValue")
    @NotNull
    @Size(min = 256, max = 256)
    private String ChkValue;

    public BaseResponse() {
    }

    public BaseResponse(CmdIdType CmdId,
                        String RespCode,
                        String RespDesc,
                        String MerCustId) {
        this.CmdId = CmdId;
        this.RespCode = RespCode;
        this.RespDesc = RespDesc;
        this.MerCustId = MerCustId;
    }

    protected String baseChkString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.trimToEmpty(getCmdId().name()));
        sb.append(StringUtils.trimToEmpty(getRespCode()));
        sb.append(StringUtils.trimToEmpty(getMerCustId()));
        return sb.toString();
    }
    
    /**
     * 用于做应答内容验证的ChkValue
     * 
     * @return 
     */
    public abstract String chkString();
    
    public boolean success() {
        return PnRConstant.SUCCESS_CODE.equalsIgnoreCase(RespCode);
    }

    public CmdIdType getCmdId() {
        return CmdId;
    }
    
    public String getRespCode() {
        return RespCode;
    }

    public String getRespDesc() {
        return RespDesc;
    }

    public String getMerCustId() {
        return MerCustId;
    }

    public String getChkValue() {
        return ChkValue;
    }

    public String getMerPriv() {
        return MerPriv;
    }
}
