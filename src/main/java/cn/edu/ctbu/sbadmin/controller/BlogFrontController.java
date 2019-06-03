package cn.edu.ctbu.sbadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * create @ ctbu
 * Description:
 * User: minttang
 * Date: 2019-05-13
 * Time: 17:12
 */
@Controller
public class BlogFrontController {


    @RequestMapping({"/","/blog/index"})

    public String Index(){



        return "/blog/front/index";
    }

    @RequestMapping("/blog/detail")
    public String Detail(){



        return "/blog/front/detail";
    }


}
