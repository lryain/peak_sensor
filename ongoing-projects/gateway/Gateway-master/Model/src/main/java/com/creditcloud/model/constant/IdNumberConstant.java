/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.model.constant;

/**
 *
 * @author rooseek
 */
public interface IdNumberConstant {

    /**
     * earliest year the idNumber could use, too old!
     */
    int MIN_YEAR = 1900;

    /**
     * the last four character suffix for a province code
     */
    String PROVINCE_SUFFIX = "0000";

    /**
     * the last two character suffix for a city code
     */
    String CITY_SUFFIX = "00";

    /**
     * following regions are not included in Chinese idNumber now
     */
    String TAIWANG = "71";

    String HONGKONG = "81";

    String MACAU = "82";

    /**
     * 默认的admin身份证号,要求能通过@IdNumber认证，但无法通过NCIIC认证
     */
    String DEFAULT_IDNUMBER = "11010119140101721X";
}
