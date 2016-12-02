/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.model.client;

import com.creditcloud.model.BaseObject;
import com.creditcloud.model.Location;
import com.creditcloud.model.constraints.ClientCode;
import com.creditcloud.model.enums.client.BranchType;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * 客户机构，可以为总部、分公司、办事处、加盟商等等
 *
 * @author sobranie
 */
@Data
@XmlRootElement
public class Branch extends BaseObject {

    private static final long serialVersionUID = 20130918L;

    private String id;

    /**
     * 所属Client的标识
     */
    @ClientCode
    private String clientCode;

    /**
     * 上级分支机构ID.
     *
     * 为null表示第一级机构
     */
    @NotNull
    private String parentId;

    /**
     * code 分支机构唯一码. 在Client内唯一标识分支机构，不限定格式，可以跟name一样
     */
    @NotNull
    private String code;

    /**
     *  分支机构唯一名称，不限定格式
     */
    @NotNull
    private String name;

    @NotNull
    private BranchType type;

    /**
     * 地址及位置信息
     */
    private Location location;

    /**
     * 负责人. 多个分支机构可以有同一个负责人 加盟店多有这种情况
     */
    @NotNull
    private Employee principal;

    /**
     * 联系人
     */
    @NotNull
    private Employee contactPerson;

    private String description;

    private Date timeCreated;

    private Date timeLastUpdated;

    public Branch() {
    }

    public Branch(String id,
                  String clientCode,
                  String parentId,
                  String code,
                  String name,
                  BranchType type,
                  Location location,
                  Employee principal,
                  Employee contactPerson,
                  String description) {
        this.id = id;
        this.clientCode = clientCode;
        this.parentId = parentId;
        this.code = code;
        this.name = name;
        this.type = type;
        this.location = location;
        this.principal = principal;
        this.contactPerson = contactPerson;
        this.description = description;
    }
}
