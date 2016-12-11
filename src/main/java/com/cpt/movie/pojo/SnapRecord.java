package com.cpt.movie.pojo;

import javax.jdo.annotations.*;
import java.util.Date;


/**
 * The persistent class for the snap_record database table.
 * Created by cpt72 on 2016/12/11.
 *
 *
	  create table snap_record
	 (
	 id                   int not null auto_increment comment '抢购编号',
	 uid                  int not null comment '用户编号',
	 movie_id             int not null comment '电影票编号',
	 num                  int not null comment '数量',
	 snap_time            datetime not null comment '抢购时间',
	 primary key (id)
	 );
 */
@PersistenceCapable(table="snap_record")
public class SnapRecord  {

	/**
	 * 抢购编号
	 */
	@PrimaryKey
	@Persistent(valueStrategy= IdGeneratorStrategy.IDENTITY)
	private int id;

	/**
	 * 用户编号
	 */
	private int uid;

	/**
	 * 电影票编号
	 */
	@Column(name="movie_id")
	private int movieId;

	/**
	 * 数量
	 */
	private int num;

	/**
	 * 抢购时间
	 */
	@Column(name="snap_time",allowsNull = "false")
	private Date snapTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Date getSnapTime() {
		return snapTime;
	}

	public void setSnapTime(Date snapTime) {
		this.snapTime = snapTime;
	}

	@Override
	public String toString() {
		return "SnapRecord{" +
				"id=" + id +
				", uid=" + uid +
				", movieId=" + movieId +
				", num=" + num +
				", snapTime=" + snapTime +
				'}';
	}
}