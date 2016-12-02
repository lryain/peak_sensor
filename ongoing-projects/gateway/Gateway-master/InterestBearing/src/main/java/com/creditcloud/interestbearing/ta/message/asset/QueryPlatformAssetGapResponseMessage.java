/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.interestbearing.ta.message.asset;

import com.creditcloud.interestbearing.ta.message.ResponseMessage;
import com.creditcloud.interestbearing.ta.model.ProductAssetGapMetadata;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author guohuazhang
 */
public class QueryPlatformAssetGapResponseMessage extends ResponseMessage {

    private List<ProductAssetGapMetadata> fundGaps;

    public void setFundGaps(List<ProductAssetGapMetadata> fundGaps) {
        this.fundGaps = fundGaps;
    }

    public void setFundGaps(ProductAssetGapMetadata[] fundGaps) {
        List<ProductAssetGapMetadata> list = new ArrayList<>();
        CollectionUtils.addAll(list, fundGaps);
        this.fundGaps = list;
    }

    @Override
    public String toString() {
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
        builder.setAppendStatics(false);
        builder.setAppendTransients(false);

        return builder.build();
    }
}
