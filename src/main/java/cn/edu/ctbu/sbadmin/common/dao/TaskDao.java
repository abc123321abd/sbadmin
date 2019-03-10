package cn.edu.ctbu.sbadmin.common.dao;



import java.util.List;
import java.util.Map;

import cn.edu.ctbu.sbadmin.common.core.MyMapper;
import cn.edu.ctbu.sbadmin.common.domain.TaskDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface TaskDao  extends MyMapper<TaskDO> {


}
