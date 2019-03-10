package cn.edu.ctbu.sbadmin.system.dao;




import cn.edu.ctbu.sbadmin.common.core.MyMapper;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.system.domain.MenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 * @author tms
 * @email tms2003@126.com
<<<<<<< HEAD
 * @date 2018-03-11 13:03:34
=======
 * @date 2018-03-17 17:09:56
>>>>>>> qingyu
 */
@Mapper
public interface MenuDao extends MyMapper<MenuDO> {

    List<MenuDO> listMap(Map<String, Object> map);


}
