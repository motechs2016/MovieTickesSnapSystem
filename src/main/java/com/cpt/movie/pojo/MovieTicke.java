package com.cpt.movie.pojo;

import javax.jdo.annotations.*;
import java.util.Date;


/**
 * The persistent class for the movie_tickes database table.
 * Created by cpt72 on 2016/12/11.
 *
	 create table movie_tickes
	 (
	 id                   int not null auto_increment comment '电影票编号',
	 name                 varchar(64) comment '电影票名称',
	 num                  int comment '数量',
	 add_time             datetime not null comment '添加时间',
	 start_time           datetime not null comment '开始时间',
	 end_time             datetime not null comment '结束时间',
	 primary key (id)
	 );
 * 
 */
@PersistenceCapable(table="movie_tickes")
public class MovieTicke {

	/**
	 * 电影票编号
	 */
	@PrimaryKey
	@Persistent(valueStrategy= IdGeneratorStrategy.IDENTITY)
	private int id;

	/**
	 * 电影票名称
	 */
	@Column(length = 64)
	private String name;

	/**
	 * 数量
	 */
	private int num;

	/**
	 * 添加时间
	 */
	@Column(name="add_time",allowsNull = "false")
	private Date addTime;

	/**
	 * 抢购开始时间
	 */
	@Column(name="start_time",allowsNull = "false")
	private Date startTime;

	/**
	 * 抢购结束时间
	 */
	@Column(name="end_time",allowsNull = "false")
	private Date endTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "MovieTicke{" +
				"id=" + id +
				", name='" + name + '\'' +
				", num=" + num +
				", addTime=" + addTime +
				", startTime=" + startTime +
				", endTime=" + endTime +
				'}';
	}
}