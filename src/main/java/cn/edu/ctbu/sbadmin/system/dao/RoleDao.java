package cn.edu.ctbu.sbadmin.system.dao;


import cn.edu.ctbu.sbadmin.common.core.MyMapper;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.system.domain.RoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统角色：
 *
 * @author tms
 * @date 2018-02-04
 */
@Mapper
public interface RoleDao extends MyMapper<RoleDO> {


    /**
     * 取指定roleName对应的多条记录
     *
     * @param roleName
     * @return
     */
    List<RoleDO> findByRoleName(String roleName);

    /**
     * 取指定roleSign对应的多条记录
     *
     * @param roleSign
     * @return
     */
    List<RoleDO> findByRoleSign(String roleSign);

    /**
     * test
     *
     * @param map
     * @return
     */
    List<RoleDO> listm(Map<String, Object> map);


}
