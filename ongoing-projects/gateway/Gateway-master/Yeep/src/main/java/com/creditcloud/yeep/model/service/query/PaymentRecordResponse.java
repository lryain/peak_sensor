/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.yeep.model.service.query;

import com.creditcloud.yeep.model.BaseResponse;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 投标记录
 * 
 * @author tinglany
 */
@XmlRootElement (name = "response")
public class PaymentRecordResponse extends BaseResponse {
    
    @NotNull
    private List<PaymentRecord> records;

    public PaymentRecordResponse() {
    }

    public PaymentRecordResponse(List<PaymentRecord> records, String platformNo, String code, String description) {
        super(platformNo, code, description);
        this.records = records;
    }

    @XmlElementWrapper(name="records")
    @XmlElement(name="record") 
    public List<PaymentRecord> getRecords() {
        return records;
    }

    public void setRecords(List<PaymentRecord> records) {
        this.records = records;
    }
      
}
