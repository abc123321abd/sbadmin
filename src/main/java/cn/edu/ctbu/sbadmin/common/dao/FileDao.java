package cn.edu.ctbu.sbadmin.common.dao;


import java.util.List;
import java.util.Map;

import cn.edu.ctbu.sbadmin.common.core.MyMapper;
import cn.edu.ctbu.sbadmin.common.domain.DictDO;
import cn.edu.ctbu.sbadmin.common.domain.FileDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件上传
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface FileDao  extends MyMapper<FileDO> {


}
