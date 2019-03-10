package cn.edu.ctbu.sbadmin.system.dao;


import cn.edu.ctbu.sbadmin.common.core.MyMapper;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.system.domain.ConfigDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * @author Fromtech
 * @email tms@fromtech.net
 * @date 2018-11-19 15:45:15
 */
@Mapper
public interface ConfigDao extends MyMapper<ConfigDO> {


}
