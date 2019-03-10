package cn.edu.ctbu.sbadmin.system.webapi;


import cn.edu.ctbu.sbadmin.common.utils.MD5Utils;
import cn.edu.ctbu.sbadmin.common.utils.R;
import cn.edu.ctbu.sbadmin.common.utils.RandomValidateCodeUtil;
import cn.edu.ctbu.sbadmin.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/webapi/login")
@Slf4j
public class LoginRestController {
    @RequestMapping("validateUser")
    public R validateUser(String username, String password,String verify,HttpServletRequest request ){

        try {
            //从session中获取随机数
            String random = (String) request.getSession().getAttribute(RandomValidateCodeUtil.RANDOMCODEKEY);
            if (StringUtils.isBlank(verify)) {
                return R.error(-2,"请输入验证码");
            }
            if (random.equals(verify)) {
            } else {
                return R.error(-2,"请输入正确的验证码");
            }
        } catch (Exception e) {
            log.error("验证码校验失败", e);
            return R.error(-2,"验证码校验失败");
        }


        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return R.ok();
        } catch (AuthenticationException e) {
            return R.error("用户或密码错误");
        }
    }
}
