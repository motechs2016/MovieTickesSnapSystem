package com.cpt.movie.service.impl;

import com.cpt.movie.dao.MovieTickeDao;
import com.cpt.movie.dao.SnapRecordDao;
import com.cpt.movie.dao.SnopProcesureDao;
import com.cpt.movie.dto.MovieDTO;
import com.cpt.movie.dto.SnapMessageDto;
import com.cpt.movie.dto.SnapResultEnum;
import com.cpt.movie.log.AutoLogger;
import com.cpt.movie.pojo.MovieTicke;
import com.cpt.movie.pojo.SnapRecord;
import com.cpt.movie.service.EmailService;
import com.cpt.movie.service.SnapService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by cpt72 on 2016/12/12.
 */
@Service
public class SnapServiceImpl implements SnapService {

    @AutoLogger
    private Logger logger;

    @Autowired
    private Environment env;

    @Autowired
    @Qualifier("snopProcesureDaoImpl")
    private SnopProcesureDao snopProcesureDao;

    @Autowired
    @Qualifier("movieTickeDaoImpl")
    private MovieTickeDao movieTickeDao;

    @Autowired
    @Qualifier("snapRecordDaoImpl")
    private SnapRecordDao snapRecordDao;

    @Autowired
    @Qualifier("emailServiceImpl")
    private EmailService emailService;

    @Override
    public SnapMessageDto snapMovie(int uid, String email, int movieId, String md5) {
        return snapMovie(uid,email, movieId, md5, 1);
    }

    @Override
    public SnapMessageDto snapMovie(int uid, String email,int movieId, String md5, int num) {
        logger.debug("snapMovie id movieId:"+uid+" "+movieId);
        if (uid <= 0 || movieId <= 0) {
            logger.debug("param error!");
            //参数不正确
            return new SnapMessageDto(SnapResultEnum.PARAM_ERROR);
        }
        if (md5 == null || !md5.equals(getMovieMd5(movieId))) {
            logger.debug("md5 error!");
            //校验码不正确
            return new SnapMessageDto(SnapResultEnum.MOVIE_MD5_ERROR);
        }
        MovieTicke movieTicke = movieTickeDao.selectById(movieId);
        if (movieTicke == null) {
            //电影票不存在
            logger.debug("ticke no exist");
            return new SnapMessageDto(SnapResultEnum.MOVIE_TICKE_NOT_EXIST);
        }
        Date date = new Date();
        if (movieTicke.getStartTime().after(date)) {
            //抢购没有开始
            logger.debug("snap not begin!");
            return new SnapMessageDto(SnapResultEnum.SNAP_NOT_BEGIN);
        } else if (movieTicke.getEndTime().before(date)) {
            //抢购已经结束
            logger.debug("snap closed!");
            return new SnapMessageDto(SnapResultEnum.SNAP_CLOSED);
        }
        SnapRecord snapRecord = snapRecordDao.selectByUidAndMovieId(uid, movieId);

        if (snapRecord != null) {
            //已经参与抢购
            logger.debug("repeat snap!");
            return new SnapMessageDto(SnapResultEnum.REPEAT_SNAP, snapRecord);
        }
        //执行抢购 默认数量1
        int result = snopProcesureDao.callSnopProc(uid, movieId, num);
        SnapResultEnum resultEnum = SnapResultEnum.getStatus(result);
        snapRecord = snapRecordDao.selectByUidAndMovieId(uid, movieId);
        //发送邮件通知
        sendMessage(email,movieTicke.getName(),resultEnum,snapRecord);
        logger.debug("snap result:"+resultEnum.value());
        return new SnapMessageDto(resultEnum, snapRecord);
    }

    private void sendMessage(String email,String movieName,SnapResultEnum resultEnum, SnapRecord snapRecord) {
        if (resultEnum==SnapResultEnum.SUCCESS){
            String title = "恭喜您，抢购成功！";
            String content = "%s,您于 %s 参与《%s》电影票抢购活动，成功抢购电影票%s张，感谢您的参与!";
            SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = dateFormater.format(snapRecord.getSnapTime());
            content=String.format(content,email,time,movieName,snapRecord.getNum());
            logger.debug("content:"+content);
            emailService.sendEmail(email,title,content);
        }
    }

    @Override
    public MovieDTO getMovieTicke(int movieId) {
        MovieDTO movieDTO = new MovieDTO();
        MovieTicke movieTicke = movieTickeDao.selectById(movieId);
        movieDTO.setMovieTicke(movieTicke);

        if (movieTicke != null) {
            Date date = new Date();
            movieDTO.setNowTime(date);
            if (movieTicke.getStartTime().before(date) && movieTicke.getEndTime().after(date)) {
                movieDTO.setMd5(getMovieMd5(movieId));
            }
        }
        return movieDTO;
    }

    @Override
    public List<MovieTicke> getAllMovieTicke() {
        return movieTickeDao.selectAll();
    }

    private String getMovieMd5(int movieId) {
        String slat = "dj7#t4*8hfdh8&(9936648%9057^hj";
        return DigestUtils.md5DigestAsHex((slat + movieId).getBytes());
    }
}
