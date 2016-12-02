/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.payment.model.chinapnr.reconciliation;

import java.util.List;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author rooseek
 */
@Data
@NoArgsConstructor
public class FssPurchaseReconciliationResponse extends ReconciliationResponse {

    @Size(max = 512)
    private String RespExt;

    private List<FssPurchaseReconciliation> FssReconciliationList;
}
