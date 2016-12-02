/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.creditcloud.ump.model.ump.tender;

import com.creditcloud.ump.model.ump.base.BaseResponse;
import com.creditcloud.ump.model.ump.enums.CmdIdRspType;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author kdliu
 */
@Data
@ToString(callSuper=true)
public class TenderTransferResponse extends BaseResponse{
    
    private String order_id;
    
    private String mer_date;
    
    private String trade_no;
    
    private String mer_check_date;

    public TenderTransferResponse() {
        super(CmdIdRspType.PROJECT_TRANSFER_RSP);
    }
    
    public TenderTransferResponse(String sign_type, 
                                  String sign, 
                                  String mer_id, 
                                  String version, 
                                  String ret_code, 
                                  String ret_msg, 
                                  String order_id, 
                                  String mer_date, 
                                  String trade_no, 
                                  String mer_check_date) {
        super(CmdIdRspType.PROJECT_TRANSFER_RSP, sign_type, sign, mer_id, version, ret_code, ret_msg);
        this.order_id = order_id;
        this.mer_date = mer_date;
        this.trade_no = trade_no;
        this.mer_check_date = mer_check_date;
    }
    
    public TenderTransferResponse(String mer_id,
                                  String ret_code, 
                                  String ret_msg, 
                                  String order_id, 
                                  String mer_date, 
                                  String trade_no, 
                                  String mer_check_date) {
        super(CmdIdRspType.PROJECT_TRANSFER_RSP, mer_id, ret_code, ret_msg);
        this.order_id = order_id;
        this.mer_date = mer_date;
        this.trade_no = trade_no;
        this.mer_check_date = mer_check_date;
    }    
}
