/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.interestbearing.ta.message.product;

import com.creditcloud.interestbearing.ta.message.RequestMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author guohuazhang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductQueryNavsOfOneFundRequestMessage extends RequestMessage {

    private String fund_id;
    int page=0;
    int pageSize=20;
    private long minDate=-1;
    private long maxDate=-1;
    private long pubDate=-1;
}
