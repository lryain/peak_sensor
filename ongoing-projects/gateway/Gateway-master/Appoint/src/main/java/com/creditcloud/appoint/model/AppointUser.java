/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creditcloud.appoint.model;

import com.creditcloud.model.BaseObject;
import com.creditcloud.model.constraints.IdNumber;
import com.creditcloud.model.constraints.RealName;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Manager从文本中导入，有资格参与某产品认购的用户基本信息记录. TODO ApointAgent也算做AppointUser
 *
 * @author rooseek
 */
@Data
@XmlRootElement
@NoArgsConstructor
public class AppointUser extends BaseObject {

    private static final long serialVersionUID = 20140109L;

    private String id;

    @RealName
    @FormParam("name")
    private String name;

    @IdNumber
    @FormParam("idNumber")
    private String idNumber;

    /**
     * 所属机构中文全称
     */
    @NotNull
    @FormParam("branch")
    private String branch;

    /**
     * 导入的时候查询机构名存储branchId,也可能找不到机构则为空.
     * 导入文件中的机构可能没有
    */
    private String branchId;

    private Date timeRecorded;

    public AppointUser(String id, String name, String idNumber, String branch) {
        this.id = id;
        this.name = name;
        this.idNumber = idNumber;
        this.branch = branch;
    }
}
