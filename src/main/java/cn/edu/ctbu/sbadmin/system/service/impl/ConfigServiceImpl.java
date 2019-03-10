package cn.edu.ctbu.sbadmin.system.service.impl;


import cn.edu.ctbu.sbadmin.common.core.AbstractService;
import cn.edu.ctbu.sbadmin.common.domain.PageDO;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.system.dao.ConfigDao;
import cn.edu.ctbu.sbadmin.system.domain.ConfigDO;
import cn.edu.ctbu.sbadmin.system.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ConfigServiceImpl extends AbstractService<ConfigDO> implements ConfigService {
    @Autowired
    private ConfigDao configDao;



}
