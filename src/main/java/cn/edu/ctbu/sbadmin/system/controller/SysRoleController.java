package cn.edu.ctbu.sbadmin.system.controller;



import cn.edu.ctbu.sbadmin.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 角色管理视图
 */
@Controller
public class SysRoleController extends BaseController {



    /**
     * 角色文件管理的基础页面
     * @return
     */
    @RequestMapping("/system/role/list")
    public  String list(){
        return "system/role/list";
    }

    /**
     * 角色文件管理的编辑及新增页面
     * @return
     */
    @RequestMapping("/system/role/edit")
    public  String edit(){
        return "system/role/edit";
    }



}
