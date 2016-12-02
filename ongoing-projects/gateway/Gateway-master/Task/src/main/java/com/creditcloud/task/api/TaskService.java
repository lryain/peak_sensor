/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.task.api;

import com.creditcloud.model.ElementCount;
import com.creditcloud.model.enums.loan.TaskStatus;
import com.creditcloud.model.enums.loan.TaskType;
import com.creditcloud.task.model.Task;
import com.creditcloud.task.model.TaskStatistics;
import com.creditcloud.task.model.UserRequestTask;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rooseek
 */
@Remote
public interface TaskService {

    /**
     * 创建新的任务
     *
     * @param clientCode
     * @param task
     * @return
     */
    Task addNew(String clientCode, Task task);

    /**
     * list task for loanRequest by task status
     * 
     * @param clientCode
     * @param requestId
     * @param status
     * @return 
     */
    List<Task> listByRequestAndStatus(String clientCode, String requestId, TaskStatus... status);
    
    /**
     * list task for employee by task status
     *
     * @param clientCode
     * @param employeeId
     * @param status
     * @return
     */
    List<Task> listByEmployee(String clientCode, String employeeId, TaskStatus... status);

    /**
     *
     * @param clientCode
     * @param employeeId
     * @param from
     * @param to
     * @param status
     * @return
     */
    List<Task> listByEmployee(String clientCode, String employeeId, Date from, Date to, TaskStatus... status);

    /**
     * list task for employee by task type
     *
     * @param clientCode
     * @param employeeId
     * @param type
     * @return
     */
    List<Task> listByEmployee(String clientCode, String employeeId, TaskType... type);

    /**
     * get task by task id
     *
     * @param clientCode
     * @param taskId
     * @return
     */
    Task getTaskById(String clientCode, String taskId);

    /**
     * update a task
     *
     * @param clientCode
     * @param task
     * @return
     */
    boolean updateTask(String clientCode, Task task);

    /**
     * 统计一个时间段内按月份员工完成的任务数
     *
     * @param clientCode
     * @param employeeId
     * @param from
     * @param to
     * @return
     */
    List<ElementCount<Date>> countMonthlyTaskByEmployee(String clientCode, String employeeId, Date from, Date to);

    /**
     * 员工一定时间内总的任务统计信息
     *
     * @param clientCode
     * @param employeeId
     * @param from
     * @param to
     * @return
     */
    TaskStatistics getTaskStatistics(String clientCode, String employeeId, Date from, Date to);
}
