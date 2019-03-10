package cn.edu.ctbu.sbadmin.system.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * Created by tms on 18/2/27.
 */
@Data
@ExcelTarget("courseEntity")
@Table(name = "sys_role")
public class RoleDO {
    /**
     * 角色id,主键
     */
    @Excel(name = "序号")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    private Integer id;

    /**
     * 角色名
     */
    @Excel(name = "角色名")
    private String roleName;
    /**
     * 角色标识
     */
    @Excel(name = "角色标识")
    private String roleSign;
    /**
     * 说明
     */
    @Excel(name = "说明")
    private String remark;
    /**
     * 建立人id
     */
    @Excel(name = "建立人id")
    private Long userIdCreate;

    /**
     * 建立时间
     */
    @Excel(name = "创建时间",  width = 30.0, exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @Excel(name = "修改时间",  width = 30.0, exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;


    /**
     * 删除时间
     */
    @Excel(name = "删除时间",  width = 30.0, exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date gmtDelete;


    /**
     * 角色拥有的权限id
     */
    @Excel(name = "角色拥有的权限id")
    private List<Integer> menuIds;

    /**
     * 是否被删除，1表示被删除
     */
    @Excel(name = "是否被删除")
    private Integer isDeleted;

    /**
     * 删除的人的id
     */
    @Excel(name = "删除的人的id")
    private Long deleteUserid;
}
