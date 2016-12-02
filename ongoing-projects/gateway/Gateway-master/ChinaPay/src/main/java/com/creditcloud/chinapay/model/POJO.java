package com.creditcloud.chinapay.model;

import com.creditcloud.model.BaseObject;
import com.fasterxml.jackson.databind.ObjectMapper;

public class POJO extends BaseObject {

    public String toJSON() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String text = mapper.writeValueAsString(this);
            return text;
        } catch (Exception e) {
            return "{}";
        }
    }

    @Override
    public String toString() {
        return toJSON();
    }
}
