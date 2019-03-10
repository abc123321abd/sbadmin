package cn.edu.ctbu.sbadmin.common.controller;


import cn.edu.ctbu.sbadmin.common.utils.ShiroUtils;
import cn.edu.ctbu.sbadmin.system.domain.UserDO;
import org.springframework.stereotype.Controller;


@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}