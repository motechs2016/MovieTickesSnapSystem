package com.cpt.movie.service.impl;

import com.cpt.movie.log.AutoLogger;
import com.cpt.movie.service.EmailService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by cpt72 on 2016/12/15.
 */
@Service
public class EmailServiceImpl implements EmailService {
    @AutoLogger
    private Logger logger;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    private BlockingQueue<SimpleMailMessage> messageQueue;
    private Thread thread;

    public EmailServiceImpl() {
        messageQueue = new LinkedBlockingQueue<>();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        SimpleMailMessage message = messageQueue.take();
                        if(env.getProperty("email.send.enable").equals("true")){
                            logger.debug("sending eamil to " + message.getTo()[0]);
                            mailSender.send(message);
                            logger.debug("send done!");
                        }
                        else {
                            logger.debug("mail.send.enable is false!");
                        }
                    } catch (Exception e) {
                        logger.error("send email error:" + e.getMessage(), e);
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public boolean sendEmail(String email, String content) {
        return sendEmail(email, env.getProperty("email.title"), content);
    }

    @Override
    public boolean sendEmail(String email, String title, String content) {
        SimpleMailMessage message = createMessage(email, title, content);
        try {
            messageQueue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private SimpleMailMessage createMessage(String email, String title, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(env.getProperty("spring.mail.username"));
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(content);
        logger.debug("create message done!");
        return simpleMailMessage;
    }
}
