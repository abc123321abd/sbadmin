package cn.edu.ctbu.sbadmin.system.service.impl;


import cn.edu.ctbu.sbadmin.common.core.AbstractService;
import cn.edu.ctbu.sbadmin.common.domain.PageDO;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.system.dao.RoleDao;
import cn.edu.ctbu.sbadmin.system.dao.RoleMenuDao;
import cn.edu.ctbu.sbadmin.system.dao.UserRoleDao;
import cn.edu.ctbu.sbadmin.system.domain.RoleDO;
import cn.edu.ctbu.sbadmin.system.domain.RoleMenuDO;
import cn.edu.ctbu.sbadmin.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by tms on 18/2/27.
 */
@Transactional
@Service
public class RoleServiceImpl extends AbstractService<RoleDO> implements RoleService {

    @Autowired
    RoleDao roleDao;
    @Autowired
    UserRoleDao userRoleDao;
    @Autowired
    RoleMenuDao roleMenuDao;



    @Override
    public List<RoleDO> getByRoleName(String roleName) { return roleDao.findByRoleName(roleName); }

    @Override
    public List<RoleDO> getByRoleSign(String roleSign) { return roleDao.findByRoleSign(roleSign); }





    public List<RoleDO> list(Long userId) {
        List<Long> rolesIds = userRoleDao.listRoleId(userId);
        List<RoleDO> roles = roleDao.listm(new HashMap<>(16));
        for (RoleDO roleDO : roles) {
            roleDO.setRoleSign("false");
            for (Long roleId : rolesIds) {
                long a=roleDO.getId();
                long b=roleId;
                if (Objects.equals(a, b)) {
                    roleDO.setRoleSign("true");
                    break;
                }
            }
        }
        return roles;
    }
//    /**
//     * 获取用户对应得角色
//     * @param mquery
//     * @param userId
//     * @return
//     */
//    @Override
//    public List<SysRoleDO> list(MQuery mquery,long userId) {
//        List<Long> rolesIds = userRoleDao.listRoleId(userId);//"userId"
//
//      //  List<SysRoleDO> roles = sysRoleDao.list(new HashMap<>(16));
//        List<SysRoleDO> roles = sysRoleDao.list(mquery);//new HashMap<>(16)
//        for (SysRoleDO roleDO : roles) {
//            roleDO.setRoleSign("false");
//            for (Long roleId : rolesIds) {
//                if (Objects.equals(roleDO.getId(), roleId)) {
//                    roleDO.setRoleSign("true");
//                    break;
//                }
//            }
//        }
//        return roles;
//    }

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @Override
    public int save(RoleDO role) {
        int count = super.save(role);
        List<Integer> menuIds = role.getMenuIds();
        Integer roleId = role.getId();
        List<RoleMenuDO> rms = new ArrayList<>();
        for (Integer menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        roleMenuDao.removeByRoleId(roleId);
        if (rms.size() > 0) {
            roleMenuDao.batchSave(rms);
        }
        return count;
    }

    /**
     * 更新
     *
     * @param role
     * @return
     */
    @Override
    public int update(RoleDO role) {
        int r = super.update(role);
        List<Integer> menuIds = role.getMenuIds();
        Integer roleId = role.getId();

        roleMenuDao.removeByRoleId(roleId);
        List<RoleMenuDO> rms = new ArrayList<>();
        for (Integer menuId : menuIds) {
            if(menuId!=null && menuId>=0){
                RoleMenuDO rmDo = new RoleMenuDO();
                rmDo.setRoleId(roleId);
                rmDo.setMenuId(menuId);
                rms.add(rmDo);
            }

        }
        if (rms.size() > 0) {
            roleMenuDao.insertList(rms);
        }
        return r;
    }



}
