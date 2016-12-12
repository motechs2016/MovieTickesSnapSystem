package com.cpt.movie.dto;

/**
 * 抢购结果枚举
 * Created by cpt72 on 2016/12/12.
 */
public enum SnapResultEnum {
    NOT_ENOUGH(-3,"库存不足！" ),
    SNOP_FAIL(-2,"抢购失败！" ),
    REPEAT_SNOP(-1,"重复抢购！" ),
    SNOP_ERROR(0,"抢购错误！" ),
    SUCCESS(1,"抢购成功！" ),
    PARAM_ERROR(2,"参数错误！"),
    MOVIE_MD5_ERROR(3, "电影票校验错误！"),
    MOVIE_TICKE_NOT_EXIST(4,"电影票不存在！" ),
    SNAP_NOT_BEGIN(5, "抢购未开始！"),
    SNAP_CLOSED(6, "抢购已结束！");
    private int _key;
    private String _value;

    private SnapResultEnum(int key, String value){
        _key = key;
        _value = value;
    }

    public String value() {
        return _value;
    }

    public int key() {
        return _key;
    }

    public static SnapResultEnum getStatus(int status){

        for (SnapResultEnum resultEnum:SnapResultEnum.values()){
            if(status==resultEnum.key())
                return resultEnum;
        }
        return null;
    }

}
