package com.cpt.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


/**
 * Created by cpt72 on 2016/12/14.
 */
@Service
public interface EmailService {

    /**
     * 给指定邮箱发邮件发送
     * @param email 邮箱
     * @param content 邮件内容
     * @return 状态 底层用队列实现，这里是加入队列状态
     */
    boolean sendEmail(String email,String content);

    /**
     * 给指定邮箱发邮件发送
     * @param email 邮箱
     * @param title 邮件主题
     * @param content 邮件内容
     * @return 状态 底层用队列实现，这里是加入队列状态
     */
    boolean sendEmail(String email,String title,String content);

}
