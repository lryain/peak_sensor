/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.service.impl;

import com.creditcloud.service.model.MarketControl;
import com.creditcloud.service.MarketControlService;

/**
 * general portal for control impl/procedure in Market</p>
 *
 * @author rooseek
 */
public class AbstractMarketControlService implements MarketControlService {

    @Override
    public boolean cancelLoan(String clientCode, String loanId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean cancelFundingInvest(String clientCode, String investId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean cancelFundingProject(String clientCode, String projectId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateMarketControl(String clientCode, MarketControl control) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MarketControl getMarketControl(String clientCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
