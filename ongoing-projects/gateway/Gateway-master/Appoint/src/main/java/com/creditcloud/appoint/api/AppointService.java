/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.appoint.api;

import com.creditcloud.appoint.enums.AppointRequestStatus;
import com.creditcloud.appoint.model.AppointRequest;
import com.creditcloud.appoint.model.AppointResult;
import com.creditcloud.model.criteria.PageInfo;
import com.creditcloud.model.enums.Realm;
import com.creditcloud.model.misc.PagedResult;
import com.creditcloud.model.misc.RealmEntity;
import javax.ejb.Remote;

/**
 *
 * @author rooseek
 */
@Remote
public interface AppointService {

    /**
     * 获取AppointRequest
     *
     * @param requestId
     * @return
     */
    AppointRequest getRequestById(String requestId);

    /**
     * 用户认购预约,预约对象可以是众筹，标的，理财等
     *
     * @param userId
     * @param amount
     * @param targe
     * @return
     */
    AppointRequest appoint(String userId, int amount, RealmEntity targe);

    /**
     * 更新AppointRequest的status
     *
     * @param status
     * @param requestIds
     */
    void markRequestStatus(AppointRequestStatus status, String... requestIds);

    /**
     * 根据认购产品列出所有的认购申请
     *
     * @param target
     * @param pageInfo
     * @param status
     * @return
     */
    PagedResult<AppointRequest> listRequestByTarget(RealmEntity target,
                                                    PageInfo pageInfo,
                                                    AppointRequestStatus... status);

    /**
     * 计算某产品的预约次数
     * @param target
     * @param status
     * @return 
     */
    int countRequestByTarget(RealmEntity target,
                             AppointRequestStatus... status);
    
    /**
     * 根据认购人列出所有认购
     *
     * @param userId
     * @param pageInfo
     * @param status
     * @return
     */
    PagedResult<AppointRequest> listRequestByUser(String userId,
                                                  PageInfo pageInfo,
                                                  AppointRequestStatus... status);

    /**
     * 根据认购人和认购产品列出所有认购
     *
     * @param userId
     * @param targe
     * @param pageInfo
     * @param status
     * @return
     */
    PagedResult<AppointRequest> listRequestByUserAndTarget(String userId,
                                                           RealmEntity targe,
                                                           PageInfo pageInfo,
                                                           AppointRequestStatus... status);
    
    /**
     * 根据状态列出预约
     * @param pageInfo
     * @param status
     * @return 
     */
    PagedResult<AppointRequest> listByStatus(PageInfo pageInfo, AppointRequestStatus... status);
    
    /**
     * 根据预约主体的类型列出预约
     * @param pageInfo
     * @param realm
     * @param status
     * @return 
     */
    PagedResult<AppointRequest> listRequestByTargetRealm(PageInfo pageInfo, Realm realm, AppointRequestStatus... status);
    /**
     * 根据用户id和预约主体类型查询预约
     *
     * @param pageInfo 查询页码和每页显示数量
     * @param realm 主体类型
     * @param userId 用户id
     * @param status 预约状态
     * @return
     */
    public PagedResult<com.creditcloud.appoint.model.AppointRequest> listRequest(
            PageInfo pageInfo,
            Realm realm,
            String userId,
            AppointRequestStatus... status);
}
