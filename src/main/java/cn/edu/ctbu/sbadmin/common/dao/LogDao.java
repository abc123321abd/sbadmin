package cn.edu.ctbu.sbadmin.common.dao;

import cn.edu.ctbu.sbadmin.common.core.MyMapper;
import cn.edu.ctbu.sbadmin.common.domain.DictDO;
import cn.edu.ctbu.sbadmin.common.domain.LogDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface LogDao extends MyMapper<LogDO> {


}
