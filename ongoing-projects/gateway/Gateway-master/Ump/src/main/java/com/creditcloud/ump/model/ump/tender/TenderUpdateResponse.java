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
public class TenderUpdateResponse extends BaseResponse{
    
    private String mer_check_date;
    
    private String project_state;

    public TenderUpdateResponse() {
        super(CmdIdRspType.MER_UPDATE_PROJECT_RSP);
    }
}
