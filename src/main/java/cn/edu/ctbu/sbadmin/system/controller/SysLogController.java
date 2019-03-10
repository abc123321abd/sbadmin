package cn.edu.ctbu.sbadmin.system.controller;



import cn.edu.ctbu.sbadmin.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tms on 18/10/20.
 */
@Controller
public class SysLogController extends BaseController {

    /**
     * 日志文件管理的基础页面
     * @return
     */
    @RequestMapping("/system/log/list")
    public  String list(){
        return "system/log/list";
    }

    /**
     * 日志文件管理的编辑及新增页面
     * @return
     */
    @RequestMapping("/system/log/edit")
    public  String Log(){
        return "system/log/edit";
    }



}
