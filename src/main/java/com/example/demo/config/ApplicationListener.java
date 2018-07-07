package com.example.demo.config;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.example.demo.entity.ScheduleJob;
import com.example.demo.service.JobService;
import com.example.demo.util.ScheduleUtil;
import com.example.demo.util.ServiceException;

import java.util.List;

@Component
public class ApplicationListener implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JobService jobService;

    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(String... args) throws Exception {

        // Run schedule job when Application startup
        List<ScheduleJob> scheduleJobList = jobService.getAllEnableJob();
        for (ScheduleJob scheduleJob : scheduleJobList) {
            try {
                CronTrigger cronTrigger = ScheduleUtil.getCronTrigger(scheduler, scheduleJob);
                System.out.println(applicationContext);
                if (cronTrigger == null){
                    ScheduleUtil.createScheduleJob(scheduler, scheduleJob);
                }else{
                    ScheduleUtil.updateScheduleJob(scheduler, scheduleJob);
                }
                logger.info("Startup {}-{} success", scheduleJob.getJobGroup(), scheduleJob.getJobName());
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }
}
