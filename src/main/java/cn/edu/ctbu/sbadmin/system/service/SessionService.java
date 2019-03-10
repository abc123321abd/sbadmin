package cn.edu.ctbu.sbadmin.system.service;


import cn.edu.ctbu.sbadmin.common.core.BaseService;
import cn.edu.ctbu.sbadmin.system.domain.ConfigDO;
import cn.edu.ctbu.sbadmin.system.domain.UserDO;
import cn.edu.ctbu.sbadmin.system.domain.UserOnline;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface SessionService  {


	List<UserDO> listOnlineUser();

	Collection<Session> sessionList();
	
	boolean forceLogout(String sessionId);
}
