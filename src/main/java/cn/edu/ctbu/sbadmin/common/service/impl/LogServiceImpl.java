package cn.edu.ctbu.sbadmin.common.service.impl;

import java.util.List;

import cn.edu.ctbu.sbadmin.common.core.AbstractService;
import cn.edu.ctbu.sbadmin.common.core.BaseService;
import cn.edu.ctbu.sbadmin.common.dao.LogDao;
import cn.edu.ctbu.sbadmin.common.domain.DictDO;
import cn.edu.ctbu.sbadmin.common.domain.LogDO;
import cn.edu.ctbu.sbadmin.common.domain.PageDO;
import cn.edu.ctbu.sbadmin.common.service.LogService;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



@Service
public class LogServiceImpl extends AbstractService<LogDO> implements LogService {
	@Autowired
	LogDao logMapper;




	public PageDO<LogDO> queryList(MQuery query) {
		Long total =  super.count(query);
		List<LogDO> logs =  super.list(query);
		PageDO<LogDO> page = new PageDO<>();
		page.setTotal(total.intValue());
		page.setRows(logs);
		return page;
	}


}
