/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.lianlianpay.request;

import com.creditcloud.lianlianpay.base.BaseRequest;
import lombok.Data;

/**
 *
 * @author tinglany
 */
@Data
public class PayResultRequest extends BaseRequest {

    //商户唯一订单号
    private String no_order;

    //商户订单时间
    private String dt_order;

    //商户编号
    private String oid_paybill;

    //查询版本号
    private String query_version;

    public PayResultRequest() {
    }

    public PayResultRequest(String oid_partner,
                            String sign_type,
                            String sign,
                            String no_order,
                            String dt_order,
                            String oid_paybill,
                            String query_version) {
        super(oid_partner, sign_type, sign);
        this.no_order = no_order;
        this.dt_order = dt_order;
        this.oid_paybill = oid_paybill;
        this.query_version = query_version;
    }

}
