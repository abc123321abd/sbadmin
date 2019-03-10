package cn.edu.ctbu.sbadmin.system.service.impl;


import cn.edu.ctbu.sbadmin.common.core.AbstractService;
import cn.edu.ctbu.sbadmin.common.domain.PageDO;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.system.dao.DeptDao;
import cn.edu.ctbu.sbadmin.system.domain.DeptDO;
import cn.edu.ctbu.sbadmin.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class DeptServiceImpl  extends AbstractService<DeptDO> implements DeptService {
	@Autowired
	private DeptDao deptDao;

}
