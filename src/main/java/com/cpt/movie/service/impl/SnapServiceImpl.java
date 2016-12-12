package com.cpt.movie.service.impl;

import com.cpt.movie.dao.MovieTickeDao;
import com.cpt.movie.dao.SnapRecordDao;
import com.cpt.movie.dao.SnopProcesureDao;
import com.cpt.movie.dto.MovieDTO;
import com.cpt.movie.dto.SnapMessageDto;
import com.cpt.movie.dto.SnapResultEnum;
import com.cpt.movie.pojo.MovieTicke;
import com.cpt.movie.pojo.SnapRecord;
import com.cpt.movie.service.SnapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by cpt72 on 2016/12/12.
 */
public class SnapServiceImpl implements SnapService {
    @Autowired
    @Qualifier("snopProcesureDaoImpl")
    private SnopProcesureDao snopProcesureDao;

    @Autowired
    @Qualifier("movieTickeDaoImpl")
    private MovieTickeDao movieTickeDao;

    @Autowired
    @Qualifier("snapRecordDaoImpl")
    private SnapRecordDao snapRecordDao;

    @Override
    public SnapMessageDto snapMovie(int uid, int movieId, String md5) {
        return snapMovie(uid,movieId,md5,1);
    }

    @Override
    public SnapMessageDto snapMovie(int uid, int movieId, String md5, int num) {
        if(uid<=0||movieId<=0){
            //参数不正确
            return new SnapMessageDto(SnapResultEnum.PARAM_ERROR);
        }
        if(md5==null||!md5.equals(getMovieMd5(movieId))){
            //校验码不正确
            return new SnapMessageDto(SnapResultEnum.MOVIE_MD5_ERROR);
        }
        MovieTicke movieTicke = movieTickeDao.selectById(movieId);
        if (movieTicke==null){
            //电影票不存在
            return new SnapMessageDto(SnapResultEnum.MOVIE_TICKE_NOT_EXIST);
        }
        Date date = new Date();
        if (movieTicke.getStartTime().after(date)){
            //抢购没有开始
            return new SnapMessageDto(SnapResultEnum.SNAP_NOT_BEGIN);
        }else if(movieTicke.getEndTime().before(date)){
            //抢购已经结束
            return new SnapMessageDto(SnapResultEnum.SNAP_CLOSED);
        }
        List<SnapRecord> snapRecords = snapRecordDao.selectByUidAndMovieId(uid, movieId);

        if(snapRecords!=null&&snapRecords.size()>0)
        {
            //已经参与抢购
            return new SnapMessageDto(SnapResultEnum.REPEAT_SNOP);
        }
        //执行抢购 默认数量1
        int result=snopProcesureDao.callSnopProc(uid,movieId,num);

        return  new SnapMessageDto(SnapResultEnum.getStatus(result));
    }

    @Override
    public MovieDTO MovieTicke(int movieId) {
        MovieDTO movieDTO=new MovieDTO();
        MovieTicke movieTicke = movieTickeDao.selectById(movieId);
        movieDTO.setMovieTicke(movieTicke);
        if (movieTicke!=null){
            Date date = new Date();
            if(movieTicke.getStartTime().after(date)&&movieTicke.getEndTime().after(date)){
                movieDTO.setMd5(getMovieMd5(movieId));
            }
        }
        return movieDTO;
    }

    @Override
    public List<MovieTicke> getAllMovieTicke() {
        return movieTickeDao.selectAll();
    }

    private String getMovieMd5(int movieId){
        String slat="dj7#t4*8hfdh8&(9936648%9057^hj";
        return DigestUtils.md5DigestAsHex((slat+movieId).getBytes());
    }
}
