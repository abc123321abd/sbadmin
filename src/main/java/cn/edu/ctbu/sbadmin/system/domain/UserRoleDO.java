package cn.edu.ctbu.sbadmin.system.domain;

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
 * 用户与角色对应关系
 * 
 * @author tms
 * @email tms2003@126.com
 * @date 2018-03-17 19:17:37
 */
@Getter
@Setter
@ToString
@Table(name = "sys_user_role")
public class UserRoleDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
	private Long id;
	//用户ID
	private Long userId;
	//角色ID
	private Long roleId;
	//是否被删除
	private Integer isDeleted;
	//删除时间
	private Date gmtDelete;
	//删除人的id
	private Integer deleteUserid;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
