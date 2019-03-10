package cn.edu.ctbu.sbadmin.system.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 *
 *
 * @author tms
 * @email tms2003@126.com
 * @date 2018-03-17 16:56:48
 */
@Getter
@Setter
@ToString
@Table(name = "sys_role_menu")
public class RoleMenuDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
	private Long id;
	//角色编号
	private Integer roleId;
	//菜单编号
	private Integer menuId;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
