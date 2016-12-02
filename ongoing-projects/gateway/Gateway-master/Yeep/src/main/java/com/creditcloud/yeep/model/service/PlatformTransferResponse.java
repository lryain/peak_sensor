/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.yeep.model.service;

import com.creditcloud.yeep.model.BaseResponse;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author tinglany
 */

@XmlRootElement(name = "response")
public class PlatformTransferResponse extends BaseResponse {

    public PlatformTransferResponse() {
    }
    
    public PlatformTransferResponse(String platformNo,
                                    String code,
                                    String description) {
        super(platformNo,code,description);
    }

}
