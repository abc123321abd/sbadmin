package cn.edu.ctbu.sbadmin.common.dao;



import java.util.List;
import java.util.Map;

import cn.edu.ctbu.sbadmin.common.core.MyMapper;
import cn.edu.ctbu.sbadmin.common.domain.DictDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface DictDao extends MyMapper<DictDO> {

    List<DictDO> listType();

}
