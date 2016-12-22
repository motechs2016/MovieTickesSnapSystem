package lab.io.rush.controller;

import lab.io.rush.dto.response.ResponseMessage;
import lab.io.rush.log.AutoLogger;
import lab.io.rush.service.LoginService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 登录相关web接口
 * Created by cpt72 on 2016/12/12.
 */
@Controller
public class LoginController {
    @AutoLogger
    private Logger logger;

    @Autowired
    @Qualifier("loginServiceImpl")
    private LoginService loginService;

    /**
     * 登录接口
     *
     * @param email 电子邮箱
     * @return 登录状态
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage login(HttpSession session, @RequestParam String email) {
        logger.debug("enter into snapMovieTicke email=" + email);
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            String pattern = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(email);
            if(!m.matches()){
                throw new Exception("邮箱格式不正确！");
            }

            int uid = loginService.loginByEmail(email);
            session.setAttribute("uid", uid);
            session.setAttribute("email", email);
            responseMessage.setData(uid);
        } catch (Exception e) {
            responseMessage.setStatus(false);
            responseMessage.setException(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return responseMessage;
    }

    /**
     * 检查登录状态接口
     *
     * @return 已经登录的电子邮箱
     */
    @RequestMapping(value = "checkLogin")
    @ResponseBody
    public ResponseMessage checkLogin(HttpSession session) {
        logger.debug("enter into checkLogin");
        ResponseMessage responseMessage = new ResponseMessage();

        Object email = session.getAttribute("email");
        if (email != null) {
            responseMessage.setData(email);
        } else {
            responseMessage.setStatus(false);
        }
        return responseMessage;
    }

    /**
     * 登出接口，登出后，返回主页
     */
    @RequestMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse httpResponse) throws IOException {
        session.removeAttribute("uid");
        session.removeAttribute("email");
        httpResponse.sendRedirect("/index");
    }
}
