package cn.edu.ctbu.sbadmin.system.service;



import cn.edu.ctbu.sbadmin.common.core.BaseService;
import cn.edu.ctbu.sbadmin.common.domain.TreeDO;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.system.domain.ConfigDO;
import cn.edu.ctbu.sbadmin.system.domain.MenuDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 菜单管理
 *
 * @author tms
 * @email tms2003@126.com
 */
@Service
public interface MenuService  extends BaseService<MenuDO> {



    TreeDO<MenuDO> getTree();

    TreeDO<MenuDO> getTree(Integer id);
    String listMenuIdByRoleId(Integer roleId);

    void SaveRoleMenu(Integer roleId, Integer[] menuIds);

    Set<String> listPerms(Long userId);

    List<TreeDO<MenuDO>> listMenuTree(Long userid);


}
