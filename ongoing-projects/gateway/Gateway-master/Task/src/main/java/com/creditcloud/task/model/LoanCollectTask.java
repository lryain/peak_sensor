/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.task.model;

import com.creditcloud.model.enums.loan.TaskStatus;
import com.creditcloud.model.enums.loan.TaskType;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * 催款任务
 *
 * @author rooseek
 */
@Data
@XmlRootElement
public class LoanCollectTask extends Task {

    private static final long serialVersionUID = 20131010L;

    /**
     * 催款任务对应的贷款还款
     */
    @NotNull
    private String loanRepaymentId;

    public LoanCollectTask() {
        super();
    }

    public LoanCollectTask(String loanRepaymentId,
                           String id,
                           String parentId,
                           String employeeId,
                           String title,
                           String description,
                           TaskStatus status,
                           Date timeStarted,
                           Date timeFinished,
                           int taskProfit,
                           int employeeProfit,
                           boolean profitPayed) {
        super(id,
              parentId,
              employeeId,
              title,
              description,
              TaskType.LOAN_COLLECT,
              status,
              timeStarted,
              timeFinished,
              taskProfit,
              employeeProfit,
              profitPayed);
        this.loanRepaymentId = loanRepaymentId;
    }
}
