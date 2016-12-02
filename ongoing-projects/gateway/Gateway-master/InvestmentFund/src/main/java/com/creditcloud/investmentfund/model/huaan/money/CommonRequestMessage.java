/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.investmentfund.model.huaan.money;

import com.creditcloud.investmentfund.api.utils.StringUtils;
import com.creditcloud.investmentfund.api.utils.xml.CDataXmlOutputFactoryImpl;
import com.creditcloud.investmentfund.constant.FundInterfaceConstants;
import com.fasterxml.aalto.stax.InputFactoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 华安货币基金请求报文通用部分<br>
 * 定义参考 : 华安云中信通讯接口说明_new.doc <br>
 *
 * @author guohuazhang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class CommonRequestMessage extends CommonMessage {

    private final String vernum = "20140401";//	8位	版本号(20140401)

    protected String MerchantId;//	8位	商家ID	是
    protected String MerchantDate;//	8位	商户日期	是

    protected String RetURL;//	100位	返回地址	是
    protected String Memo;//	120位	定单描述	否

    public void setMerchantId(String merchantId) {
        MerchantId = merchantId;
        setMerchantid(merchantId);
    }

    public abstract Map<String, String> toMapFromXMLPayloadParameters();

    /**
     * 从用户提供的字段构造XML content明文
     *
     * @return
     */
    public String buildXMLPayload() {
        try {
            Map<String, String> map = toMapFromXMLPayloadParameters();
            final String rootXMLNodeName = "order";
            XmlFactory factory = new XmlFactory(new InputFactoryImpl(),
                                                new CDataXmlOutputFactoryImpl());
            XmlMapper xmlMapper = new XmlMapper(factory);
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
            String xml = xmlMapper.writeValueAsString(map);
            Document doc = DocumentHelper.parseText(xml);
            Element root = doc.getRootElement();
            root.setName(rootXMLNodeName);
            doc.setXMLEncoding(FundInterfaceConstants.HUA_AN_MONEY_FUND_HTTP_ENCODING);
            xml = doc.asXML();
            setContentXMLPayloadPlainText(xml);
            return xml;
        } catch (JsonProcessingException | DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();

        map.put("vernum", StringUtils.nonNull(getVernum()));
        map.put("platformid", StringUtils.nonNull(this.getPlatformid()));
        map.put("merchantid", StringUtils.nonNull(this.getMerchantid()));
        map.put("sysdate", StringUtils.nonNull(this.getSysdate()));
        map.put("systime", StringUtils.nonNull(this.getSystime()));
        map.put("txcode", StringUtils.nonNull(this.getTxcode()));
        map.put("seqno", StringUtils.nonNull(this.getSeqno()));
        map.put("maccode", StringUtils.nonNull(this.getMaccode()));
        map.put("content", StringUtils.nonNull(this.getContentXMLPayload()));

        return map;
    }
}
