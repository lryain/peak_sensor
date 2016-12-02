/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.interestbearing.api;

import com.creditcloud.interestbearing.model.UserRegistrationInfo;
import java.util.List;
import javax.ejb.Remote;

/**
 * 余额生息用户签约登记接口<br>
 * 供 CreditMarket 使用
 *
 * @author guohuazhang
 */
@Remote
public interface InterestBearingUserRegistrationService {

    /**
     * 登记一个新用户
     *
     * @param registrationInfo
     * @return 成功则返回内部登记号；失败则返回0长度字符串
     */
    String registerNewUser(UserRegistrationInfo registrationInfo);

    /**
     * 查询用户是否已经开通余额生息
     *
     * @param userId
     * @return true-已经开通;false-尚未开通;
     */
    boolean isUserRegistered(String userId);

    /**
     * 查询用户已经开通余额生息的所有信息
     *
     * @param userId
     * @return 如果已经开通，返回相关信息，如果尚未开通，返回null
     */
    UserRegistrationInfo queryUserRegistration(String userId);

    /**
     * 查询所有签约的用户签约信息
     *
     * @return
     */
    List<UserRegistrationInfo> queryAllUserRegistrations();
}
