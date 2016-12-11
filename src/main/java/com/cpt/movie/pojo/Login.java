package com.cpt.movie.pojo;

import javax.jdo.annotations.*;
import java.util.Date;


/**
 * The persistent class for the login database table.
 * Created by cpt72 on 2016/12/11.
 *
	 create table login
	 (
	 id                   int not null auto_increment comment '用户编号',
	 username             varchar(16) comment '用户名',
	 password             varchar(24) comment '密码',
	 email                varchar(32) comment '电子邮箱',
	 register_time        datetime not null comment '注册时间',
	 primary key (id)
	 );
 * 
 */

@PersistenceCapable(table="login")
public class Login  {

	/**
	 * 用户编号
	 */
	@PrimaryKey
	@Persistent(valueStrategy= IdGeneratorStrategy.IDENTITY)
	private int id;

	/**
	 * 用户名
	 */
	@Column(length = 16)
	private String username;

	/**
	 * 密码
	 */
	@Column(length = 24)
	private String password;

	/**
	 * 电子邮箱
	 */
	@Column(length = 32)
	private String email;

	/**
	 * 注册时间
	 */
	@Column(name="register_time",allowsNull = "false")
	private Date registerTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return "Login{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", registerTime=" + registerTime +
				'}';
	}
}