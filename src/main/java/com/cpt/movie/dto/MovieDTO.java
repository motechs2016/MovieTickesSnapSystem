package com.cpt.movie.dto;

import com.cpt.movie.pojo.MovieTicke;

/**
 * 电影票服务层DTO
 * Created by cpt72 on 2016/12/12.
 */
public class MovieDTO {
    /**
     * 电影票对象
     */
    private MovieTicke movieTicke;
    /**
     * 电影票校验码
     */
    private String md5;

    public MovieTicke getMovieTicke() {
        return movieTicke;
    }

    public void setMovieTicke(MovieTicke movieTicke) {
        this.movieTicke = movieTicke;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
