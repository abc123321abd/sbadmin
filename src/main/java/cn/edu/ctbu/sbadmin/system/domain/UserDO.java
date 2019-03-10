package cn.edu.ctbu.sbadmin.system.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author tms
 * @email tms2003@126.com
 * @date 2018-03-04 10:26:21
 */

@Getter
@Setter
@ToString
@Table(name = "sys_user")
public class UserDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
	private Long id;
	//用户名
	private String username;
	//真名
	private String truename;
	//密码
	private String password;
	//
	private Integer deptId;

	//部门名,不是表中的字段

	@Transient
	private String deptName;
	//邮箱
	private String email;
	//手机号
	private String mobile;
	//状态 0:禁用，1:正常
	private Integer status;
	//创建用户id
	private Long userIdCreate;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;
	//性别
	private Integer sex;
	//出身日期
	private Date birth;
	//用户头像
	private String imgUrl;
	//现居住地
	private String liveAddress;
	//爱好
	private String hobby;
	//省份
	private String province;
	//所在城市
	private String city;
	//所在地区
	private String district;
	//是否被删除
	private Integer isDeleted = 0;
	//删除时间
	private Date gmtDelete;
	//删除人的id
	private Long deleteUserid;
	//用户简介
	private String introduce;
	//签名url
	private String img;
	//备注
	private String memo;
	//角色名
	private String roleName;
	//角色ids
	private String roleIds;
	//默认主页
	private String homeUrl;
	//主管id
	private Integer managerId;
	//主管名
	private String managerName;
	//第二密码
	private String otherPassword;
	//微信id
	private String wxids;
	//微信名
	private String wxname;

}
