package cn.edu.ctbu.sbadmin.system.controller;


import cn.edu.ctbu.sbadmin.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 角色管理视图
 */
@Controller
public class SysPartController extends BaseController {

    /**
     * 角色文件管理的基础页面
     * @return
     */
    @RequestMapping("/system/dept/list")
    public  String list(){
        return "system/dept/list";
    }

    /**
     * 角色文件管理的编辑及新增页面
     * @return
     */
    @RequestMapping("/system/dept/edit")
    public  String edit(){
        return "system/dept/edit";
    }



}
