package cn.edu.ctbu.sbadmin.system.controller;


import cn.edu.ctbu.sbadmin.common.controller.BaseController;
import cn.edu.ctbu.sbadmin.common.domain.TreeDO;
import cn.edu.ctbu.sbadmin.system.domain.MenuDO;
import cn.edu.ctbu.sbadmin.system.service.MenuService;
import com.alibaba.druid.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 系统控制器页面
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {
    @Autowired
    MenuService menuService;
    /**
     * 日志文件管理的基础页面
     * @return
     */
    @RequestMapping("index")
    public  String Index(Model model){

        List<TreeDO<MenuDO>> menus = menuService.listMenuTree(getUser().getId());//     getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("name", getUser().getTruename());
        model.addAttribute("email", getUser().getEmail());
        model.addAttribute("mobile", getUser().getMobile());

        if(!StringUtils.isEmpty(getUser().getImgUrl()) ){

            model.addAttribute("picUrl",getUser().getImgUrl());

        }else {
            model.addAttribute("picUrl","/img/profile-photos/1.png");
        }
        model.addAttribute("username", getUser().getUsername());

        return "system/index";
    }







//    /**
//     * 用户管理的基础页面
//     * @return
//     */
//    @RequestMapping("user")
//    public  String User(){
////        long id=0l;
////        List<SysRoleDO> roles = sysRoleService.list(id);
////        model.addAttribute("roles", roles);
//
//        return "system/user";
//    }


    /**
     * 图标列表
     * @return
     */
    @RequestMapping("fontIcoList")
    public  String fontIcoList(){
        return "system/fontIcoList";
    }

}
