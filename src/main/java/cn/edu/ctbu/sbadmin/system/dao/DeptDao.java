package cn.edu.ctbu.sbadmin.system.dao;


import cn.edu.ctbu.sbadmin.common.core.MyMapper;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.system.domain.DeptDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * @author tms
 * @email tms2003@126.com
 * @date 2018-03-07 18:16:14
 */
@Mapper
public interface DeptDao extends MyMapper<DeptDO> {

}
