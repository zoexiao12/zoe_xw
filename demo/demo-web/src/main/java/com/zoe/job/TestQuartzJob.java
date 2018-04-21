package com.zoe.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by zoe on 2018/1/30.
 */
@Component
public class TestQuartzJob {

    @Scheduled(cron = "*/1 * * * * ?")
    public  void run(){
        System.out.println("Hello quartz! now -> " + new Date());
    }
}
