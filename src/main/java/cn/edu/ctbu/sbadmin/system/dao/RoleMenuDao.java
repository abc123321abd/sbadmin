package cn.edu.ctbu.sbadmin.system.dao;



import cn.edu.ctbu.sbadmin.common.core.MyMapper;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.system.domain.RoleMenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * @author tms
 * @email tms2003@126.com
 * @date 2018-03-17 16:56:48
 */
@Mapper
public interface RoleMenuDao  extends MyMapper<RoleMenuDO> {

    /**
     * 取指定id对应的记录
     * @param id
     * @return
     */
    RoleMenuDO get(Long id);

    /**
     * 返回查询记录的结果，支持分页
     * @param mQuery
     * @return
     */
    List<RoleMenuDO> list(MQuery mQuery);

    /**
     * 查询指定条件的计数，复杂条件支持
     * @param mQuery
     * @return
     */
    int count(MQuery mQuery);


    /**
     * 保存记录
     * @param roleMenu
     * @return
     */
    int save(RoleMenuDO roleMenu);


    /**
     * 更新记录
     * @param roleMenu
     * @return
     */
    int update(RoleMenuDO roleMenu);

    /**
     * 删除指定id的记录，逻辑删除
     * @param roleMenu
     * @return
     */
    int remove(RoleMenuDO roleMenu);

    /**
     * 删除指定id的角色，真删除
     * @param id
     * @return
     */
    int delete(Long id);



    /**
     * 批量删除角色，真删除
     * @param ids
     * @return
     */
    int batchDelete(Long[] ids);

    List<Integer> listMenuIdByRoleId(Integer roleId);
    int removeByRoleId(Integer roleId);
    int batchSave(List<RoleMenuDO> list);
}
