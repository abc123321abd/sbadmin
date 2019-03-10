package cn.edu.ctbu.sbadmin.system.dao;



import cn.edu.ctbu.sbadmin.common.core.MyMapper;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.system.domain.SysLogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统日志：
 * @author tms
 * @date 2018-02-04
 */
@Mapper
public interface SysLogDao extends MyMapper<SysLogDO> {

}
