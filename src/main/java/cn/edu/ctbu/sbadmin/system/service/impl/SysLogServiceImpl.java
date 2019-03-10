package cn.edu.ctbu.sbadmin.system.service.impl;


import cn.edu.ctbu.sbadmin.common.core.AbstractService;
import cn.edu.ctbu.sbadmin.common.domain.PageDO;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.common.utils.Query;
import cn.edu.ctbu.sbadmin.system.dao.SysLogDao;
import cn.edu.ctbu.sbadmin.system.domain.SysLogDO;
import cn.edu.ctbu.sbadmin.system.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class SysLogServiceImpl extends AbstractService<SysLogDO> implements SysLogService {
    @Autowired
    SysLogDao sysLogDao;



}


