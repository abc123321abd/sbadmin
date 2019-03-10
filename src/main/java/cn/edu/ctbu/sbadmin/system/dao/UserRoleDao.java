package cn.edu.ctbu.sbadmin.system.dao;



import cn.edu.ctbu.sbadmin.common.core.MyMapper;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.system.domain.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户与角色对应关系
 * @author tms
 * @email tms2003@126.com
 * @date 2018-03-17 19:17:37
 */
@Mapper
public interface UserRoleDao  extends MyMapper<UserRoleDO> {

    //找出对应的角色Id
    List<Long> listRoleId(Long userId);


    //找出对应用户的角色信息
    List<UserRoleDO> Listm(Long userId);


}
