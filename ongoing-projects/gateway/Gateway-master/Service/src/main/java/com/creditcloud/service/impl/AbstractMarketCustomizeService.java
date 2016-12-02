/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.service.impl;

import com.creditcloud.model.criteria.PageInfo;
import com.creditcloud.model.misc.PagedResult;
import com.creditcloud.service.MarketCustomizeService;
import com.creditcloud.service.model.misc.WechatInfo;
import com.creditcloud.sms.SMSType;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.tuple.Pair;

/**
 *
 * @author rooseek
 */
public class AbstractMarketCustomizeService implements MarketCustomizeService {

    @Override
    public int addWechatInfos(List<WechatInfo> wis) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PagedResult<com.creditcloud.service.model.misc.WechatInfo> listWechatInfoByDate(Date from, Date to, PageInfo pageInfo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public WechatInfo addWechatInfo(WechatInfo wxi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean rewardWechatInfo(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<SMSType, ConcurrentHashMap<String, Map<String, Pair<Long, Long>>>> querySmsCaptchaMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
