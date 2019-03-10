package cn.edu.ctbu.sbadmin.system.dao;


import cn.edu.ctbu.sbadmin.common.core.MyMapper;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.system.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * @author tms
 * @email tms2003@126.com
 * @date 2018-03-04 10:26:21
 */
@Mapper
public interface UserDao  extends MyMapper<UserDO> {

}
