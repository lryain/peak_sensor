/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.investmentfund.model.huaan.money.response;

import com.creditcloud.investmentfund.model.huaan.money.CommonResponseMessage;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 定义参考 : 华安云中信通讯接口说明_new.doc <br>
 *
 * @author guohuazhang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RedeemResponseMessage extends CommonResponseMessage {

    private String MerchantId;//	8位	商家ID
    private String MerchantDate;//	8位	商户日期
    private String TotalQuty;//	18位	赎回份额（单位：份额）
    private String AssignBuyer;//	12位	买家信息(客户协议号)	
    private String AssbuyerName;//	120位	客户姓名
    private String AssbuyerIdTp;//	1位	客户证件类型(0为身份证)
    private String LogisticsInfo;//	17位	交易号(对应资产)
    private String Fee;//	18位	手续费(此处为0)
    private String FundType;//	1位	交易类型（1：申购；2：赎回）
    private String CommonReturn;//	200位	公共回传字段

    @Override
    public void resolveXMLPaylod() {
        String decodedXML = this.getContentXMLPayloadPlainText();
        Map<String, String> parameters = parseXMLPayload(decodedXML);

        MerchantId = parameters.get("MerchantId");
        MerchantDate = parameters.get("MerchantDate");
        TotalQuty = parameters.get("TotalQuty");
        AssignBuyer = parameters.get("AssignBuyer");
        AssbuyerName = parameters.get("AssbuyerName");
        AssbuyerIdTp = parameters.get("AssbuyerIdTp");
        LogisticsInfo = parameters.get("LogisticsInfo");
        Fee = parameters.get("Fee");
        FundType = parameters.get("FundType");
        RetCode = parameters.get("RetCode");
        RetMsg = parameters.get("RetMsg");
        CommonReturn = parameters.get("CommonReturn");
    }
}
