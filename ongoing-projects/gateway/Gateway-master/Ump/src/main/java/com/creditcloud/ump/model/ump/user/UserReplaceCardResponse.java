/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.ump.model.ump.user;

import com.creditcloud.ump.model.ump.base.BaseResponse;
import com.creditcloud.ump.model.ump.enums.CmdIdRspType;
import lombok.Data;

/**
 *
 * @author rooseek
 */
@Data
public class UserReplaceCardResponse extends BaseResponse {

    private String user_id;

    public UserReplaceCardResponse() {
        super(CmdIdRspType.MER_REPLACE_CARD_RSP);
    }

    public UserReplaceCardResponse(String sign_type, 
                                   String sign, 
                                   String mer_id,
                                   String version,
                                   String ret_code, 
                                   String ret_msg, 
                                   String user_id) {
        super(CmdIdRspType.MER_REPLACE_CARD_RSP,
              sign_type, 
              sign,
              mer_id, 
              version,
              ret_code, 
              ret_msg);
        this.user_id = user_id;
    }
}
