package cn.edu.ctbu.sbadmin.system.service.impl;



import cn.edu.ctbu.sbadmin.common.core.AbstractService;
import cn.edu.ctbu.sbadmin.common.domain.TreeDO;
import cn.edu.ctbu.sbadmin.common.utils.BuildTree;
import cn.edu.ctbu.sbadmin.common.utils.Helper;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.system.dao.MenuDao;
import cn.edu.ctbu.sbadmin.system.dao.RoleMenuDao;
import cn.edu.ctbu.sbadmin.system.dao.UserDao;
import cn.edu.ctbu.sbadmin.system.domain.MenuDO;
import cn.edu.ctbu.sbadmin.system.domain.RoleMenuDO;
import cn.edu.ctbu.sbadmin.system.domain.UserDO;
import cn.edu.ctbu.sbadmin.system.service.MenuService;
import cn.edu.ctbu.sbadmin.system.service.UserService;
import com.google.common.primitives.Longs;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Transactional
@Service
public class MenuServiceImpl  extends AbstractService<MenuDO> implements MenuService {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private RoleMenuDao roleMenuDao;

    @Autowired
    private UserService userService;

    /**
     * 保存指定角色的对应菜单权限
     *
     * @param roleId
     * @param menuIds
     */
    @Override
    public void SaveRoleMenu(Integer roleId, Integer[] menuIds) {
        //1)先读取对应的角色id的所有权限
        List<Integer> oldPower = roleMenuDao.listMenuIdByRoleId(roleId);

        if (oldPower.size() > 0) {
            //2)不为空的话，就删除它
            Long[] powArr = new Long[oldPower.size()];
            oldPower.toArray(powArr);
            roleMenuDao.batchDelete(powArr);
        }

        //3)然后再保存对应的权限
        List<RoleMenuDO> newPowers = new ArrayList<RoleMenuDO>();
        Integer len = menuIds.length;
        for (int i = 0; i < len; i++) {
            RoleMenuDO roleMenuDO = new RoleMenuDO();
            roleMenuDO.setRoleId(roleId.intValue());
            roleMenuDO.setMenuId(menuIds[i].intValue());
            newPowers.add(roleMenuDO);
        }

        roleMenuDao.batchSave(newPowers);
    }

    @Override
    public TreeDO<MenuDO> getTree() {
        List<TreeDO<MenuDO>> trees = new ArrayList<TreeDO<MenuDO>>();
        List<MenuDO> menuDOs = menuDao.listMap(new HashMap<>(16));

        for (MenuDO sysMenuDO : menuDOs) {
            TreeDO<MenuDO> tree = new TreeDO<MenuDO>();
            tree.setId(sysMenuDO.getId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            trees.add(tree);
        }

        // 默认顶级菜单为０，根据数据库实际情况调整
        TreeDO<MenuDO> t = BuildTree.build(trees);

        return t;
    }

    @Override
    public String listMenuIdByRoleId(Integer roleId) {
        List<Integer> menuIds = roleMenuDao.listMenuIdByRoleId(roleId);
        String menuIdsStr = Helper.listToString(menuIds, ',');

        return menuIdsStr;
    }

    @Override
    public TreeDO<MenuDO> getTree(Integer id) {
        // 根据roleId查询权限
        List<MenuDO> menus = menuDao.listMap(new HashMap<String, Object>(16));
        List<Integer> menuIds = roleMenuDao.listMenuIdByRoleId(id);
        List<Integer> temp = menuIds;

        for (MenuDO menu : menus) {
            if (temp.contains(menu.getParentId())) {
                menuIds.remove(menu.getParentId());
            }
        }

        List<TreeDO<MenuDO>> trees = new ArrayList<TreeDO<MenuDO>>();
        List<MenuDO> menuDOs = menuDao.listMap(new HashMap<String, Object>(16));

        for (MenuDO sysMenuDO : menuDOs) {
            TreeDO<MenuDO> tree = new TreeDO<MenuDO>();
            tree.setId(sysMenuDO.getId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> state = new HashMap<>(16);
            Integer menuId = sysMenuDO.getId();
            if (menuIds.contains(menuId)) {
                state.put("selected", true);
            } else {
                state.put("selected", false);
            }
            tree.setState(state);
            trees.add(tree);
        }

        // 默认顶级菜单为０，根据数据库实际情况调整
        TreeDO<MenuDO> t = BuildTree.build(trees);

        return t;
    }

    @Override
    public Set<String> listPerms(Long userId) {
        //分三步走，
        //1)读取所有的ids从userid的角色中;
        UserDO userDO = userService.get(userId);
        Set<String> permsSet = new HashSet<>();
        if (userDO != null) {
            //2)从角色中读取所有的权限id;此处可以使用缓存

            //3)从id中读取所有的权限;
            String ids = userDO.getRoleIds();
            String[] userRoles = ids.split(",");
            Integer len = userRoles.length;
            List<Integer> roleids = new ArrayList<Integer>();

            for (int i=0;i<len;i++){
                if(!userRoles[i].equals("")){
                    Integer roleid = Integer.parseInt(userRoles[i]);
                    List<Integer> menuids = roleMenuDao.listMenuIdByRoleId(roleid);
                    roleids.addAll(menuids);
                }
            }

            len = roleids.size();
            //4)组成set返回字符串组
            for (Integer item:roleids) {
                MenuDO menuDO = super.get(item);
                if(menuDO != null){
                    String perm = menuDO.getPerms();
                    if(StringUtils.isNotBlank(perm)){
                        permsSet.addAll(Arrays.asList(perm.trim().split(",")));
                    }
                }
            }
        }

//        List<String> perms = menuMapper.listUserPerms(userId);
//        Set<String> permsSet = new HashSet<>();
//        for (String perm : perms) {
//            if (StringUtils.isNotBlank(perm)) {
//                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
//            }
//        }
        return permsSet;
    }

    @Override
    public List<TreeDO<MenuDO>> listMenuTree(Long userid) {
        List<TreeDO<MenuDO>> trees = new ArrayList<TreeDO<MenuDO>>();
        List<TreeDO<MenuDO>> list = new ArrayList<TreeDO<MenuDO>>();

        //1)读取所有的ids从userid的角色中;
        UserDO userDO = userService.get(userid);
        if (userDO != null) {
            //2)从角色中读取所有的权限id;此处可以使用缓存
            String ids = userDO.getRoleIds();
            String[] userRoles = ids.split(",");
            Integer len = userRoles.length;
            List<Integer> rolemenuids = new ArrayList<Integer>();
            for (int i=0; i<len; i++){
                if (!userRoles[i].equals("")){
                    Integer roleid = Integer.parseInt(userRoles[i]);
                    List<Integer> menuids = roleMenuDao.listMenuIdByRoleId(roleid);
                    rolemenuids.addAll(menuids);
                }
            }

            //Long[] arrayL = rolemenuids.toArray(new Long[rolemenuids.size()]);
            //Integer[] arrayL =  rolemenuids.toArray(new Integer[rolemenuids.size()]);
           // Long[] arrayL = Longs.toArray(rolemenuids) ;//rolemenuids.stream().mapToLong(l -> l).toArray();
           // Long[] arrayL = rolemenuids.toArray(new Long[0]);
            long[] arrayL =  Longs.toArray(rolemenuids);



            if (arrayL.length>0){
                //必须有内容
                List<MenuDO> menuDOs = super.findByIds(arrayL);

                for (MenuDO sysMenuDO : menuDOs) {
                    if (sysMenuDO!=null && sysMenuDO.getType()<2) {
                        TreeDO<MenuDO> tree = new TreeDO<MenuDO>();
                        tree.setId(sysMenuDO.getId().toString());
                        tree.setParentId(sysMenuDO.getParentId().toString());
                        tree.setText(sysMenuDO.getName());
                        tree.setUrl(sysMenuDO.getUrl());
                        tree.setIcon(sysMenuDO.getIcon());

                        if(sysMenuDO.getType() == 1){
                            //只有菜单才加入
                            tree.setTargetType("iframe-tab");
                        }

                        Map<String, Object> attributes = new HashMap<>(16);
                        attributes.put("url", sysMenuDO.getUrl());
                        attributes.put("icon", sysMenuDO.getIcon());
                        tree.setAttributes(attributes);
                        trees.add(tree);
                    }
                }
                // 默认顶级菜单为０，根据数据库实际情况调整
                list = BuildTree.buildList(trees, "0");
            }
        }

        return list;
    }

}
