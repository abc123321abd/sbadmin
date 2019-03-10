package cn.edu.ctbu.sbadmin.system.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 部门管理
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:28:36
 */
@Data
@Table(name = "sys_dept")
public class DeptDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//Id编号
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
	private Integer id;
	//父节点Id
	private Integer parentId;
	//部门名称
	private String name;
	//排序
	private Integer orderNum;
	//是否被删除
	private Integer isDeleted = 0;
	//删除时间
	private Date gmtDelete;
	//删除人的id
	private Long deleteUserid;
	//备注
	private String memo;
	//地址
	private String address;
	//电话
	private String phone;
	//所有者id
	private Long ownerId;
	//经度
	private String longtitude;
	//纬度
	private String latitude;

	/**
	 * 节点等级
	 */
	private  Integer level	;
}
