package com.cpt.movie.controller;

import com.cpt.movie.dto.MovieDTO;
import com.cpt.movie.dto.SnapMessageDto;
import com.cpt.movie.dto.response.ResponseMessage;
import com.cpt.movie.log.AutoLogger;
import com.cpt.movie.pojo.MovieTicke;
import com.cpt.movie.service.SnapService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 抢购相关web接口和页面
 * Created by cpt72 on 2016/12/12.
 */
@Controller
public class SnapController {
    @AutoLogger
    private Logger logger;

    @Autowired
    @Qualifier("snapServiceImpl")
    private SnapService snapService;

    /**
     * 首页
     */
    @RequestMapping({"/", "/index", "/home"})
    public String index(Model model) {
        try {
            List<MovieTicke> movieTickes = snapService.getAllMovieTicke();
            model.addAttribute("movieTickes", movieTickes);
            model.addAttribute("name", "test");
            logger.debug("movieTickes size=" + movieTickes.size() + "\n" + movieTickes);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return "index";
    }

    /**
     * 电影票抢购详情页面
     *
     * @param movieId 电影票编号
     */
    @RequestMapping(value = "/movie/{movieId}")
    public String getMovieTicke(@PathVariable Integer movieId, Model model) {
        logger.debug("enter into getMovieTicke movieId=" + movieId);
        try {
            MovieDTO movieDTO = snapService.getMovieTicke(movieId);
            if (movieDTO.getMovieTicke() == null)
                throw new RuntimeException("电影票抢购信息不存在！");
            model.addAttribute("movieTicke", movieDTO.getMovieTicke());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return "movie";
    }

    /**
     * 获取电影票抢购校验码
     *
     * @param movieId 电影票编号
     * @return json数据 如果抢购未开始，返回服务器时间。如果抢购开始，返回校验码
     */
    @ResponseBody
    @RequestMapping(value = "/movieMd5/{movieId}")
    public ResponseMessage getMovieTickeMd5(@PathVariable Integer movieId) {
        logger.debug("enter into getMovieTickeMd5 movieId=" + movieId);
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            MovieDTO movieTicke = snapService.getMovieTicke(movieId);
            responseMessage.setData(movieTicke);
        } catch (Exception e) {
            responseMessage.setStatus(false);
            responseMessage.setException(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return responseMessage;
    }

    /**
     * 电影票抢购接口
     *
     * @param movieId 电影票编号
     * @param md5     校验码
     * @return json数据  包含抢购的状态信息和抢购记录
     */
    @ResponseBody
    @RequestMapping("/snap/{movieId}/{md5}")
    public ResponseMessage snapMovieTicke(@PathVariable Integer movieId, @PathVariable String md5, HttpSession session) {
        logger.debug("enter into snapMovieTicke movieId=" + movieId + " md5=" + md5);
        ResponseMessage responseMessage = new ResponseMessage();
        Object uid = session.getAttribute("uid");
        if (uid == null) {
            session.removeAttribute("email");
            responseMessage.setStatus(false);
            responseMessage.setErrorCode(1);
            responseMessage.setData("未登录，请重新登录...");
            return responseMessage;
        }
        try {
            SnapMessageDto snapMessageDto = snapService.snapMovie((Integer) uid, movieId, md5);
            responseMessage.setData(snapMessageDto);
        } catch (Exception e) {
            responseMessage.setStatus(false);
            responseMessage.setException(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return responseMessage;
    }
}
