package com.creditcloud.chinapay.model.request;

import com.creditcloud.chinapay.model.POJO;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.creditcloud.chinapay.utils.StringUtils;

public abstract class SingleCutParameters extends POJO {

    private static final long serialVersionUID = 20150311L;

    protected String merId = "";

    protected final String transType = "0003";

    protected String orderNo = "";

    protected String transDate = "";

    protected String priv1 = "";

    protected final String version = "20100831";

    protected String chkValue = "";

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getUnicodePriv1() {
        String text = StringUtils.toUnicode(priv1);
        return text;
    }

    public void setTransDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStringYYYYMMDD = sdf.format(date);
        setTransDate(dateStringYYYYMMDD);
    }

    public String getVersion() {
        return version;
    }

    public String getChkValue() {
        return chkValue;
    }

    public void setChkValue(String chkValue) {
        this.chkValue = chkValue;
    }

    public String getTextToSign() {
        return new StringBuffer(merId).append(transType).append(orderNo).append(transDate).append(version).toString();
    }

    public String getTransType() {
        return transType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getPriv1() {
        return priv1;
    }

    public void setPriv1(String priv1) {
        this.priv1 = priv1;
    }
}
