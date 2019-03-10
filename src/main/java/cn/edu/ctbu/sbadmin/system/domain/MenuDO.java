package cn.edu.ctbu.sbadmin.system.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 菜单管理
 *
 * @author tms
 * @email tms2003@126.com
 */
@Data
@Table(name = "sys_menu")
public class MenuDO implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    private Integer id;
    //父菜单ID，一级菜单为0
    private Integer parentId;
    //菜单名称
    private String name;
    //菜单URL
    private String url;
    //授权(多个用逗号分隔，如：user:list,user:create)
    private String perms;
    //类型   0：目录   1：菜单   2：按钮
    private Integer type;
    //菜单图标
    private String icon;
    //排序
    private Integer orderNum;
    //创建时间
    private Date gmtCreate;
    //修改时间
    private Date gmtModified;
    //是否被删除
    private Integer isDeleted = 0;
    //删除时间
    private Date gmtDelete;
    //删除人的id
    private Long deleteUserid;
    /**
     * 节点等级
     */
    private  Integer level	;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
