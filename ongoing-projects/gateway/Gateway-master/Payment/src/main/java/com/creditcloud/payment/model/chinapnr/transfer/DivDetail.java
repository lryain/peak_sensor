/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.payment.model.chinapnr.transfer;

import com.creditcloud.model.BaseObject;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分账账户
 *
 * @author rooseek
 */
@Data
@NoArgsConstructor
@XmlRootElement
public class DivDetail extends BaseObject {

    private static final long serialVersionUID = 20131115L;

    @NotNull
    protected String DivAcctId;

    @NotNull
    protected String DivAmt;

    public DivDetail(String DivAcctId, String DivAmt) {
        this.DivAcctId = DivAcctId;
        this.DivAmt = DivAmt;
    }
}
