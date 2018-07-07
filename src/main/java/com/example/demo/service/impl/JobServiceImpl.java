package com.example.demo.service.impl;

import java.util.List;

import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.JobRepository;
import com.example.demo.entity.ScheduleJob;
import com.example.demo.service.JobService;
import com.example.demo.util.ScheduleUtil;
import com.example.demo.util.ServiceException;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
    private JobRepository jobDao;

    @Autowired
    private Scheduler scheduler;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<ScheduleJob> getAllEnableJob() {
        return jobDao.getAllEnableJob();
    }

    public ScheduleJob select(Long jobId) throws ServiceException {
        ScheduleJob scheduleJob = jobDao.findById(jobId).get();
        if (scheduleJob == null) {
            throw new ServiceException("ScheduleJob:" + jobId + " not found");
        }
        return scheduleJob;
    }

    @Transactional(rollbackFor = DataAccessException.class)
    public ScheduleJob update(Long jobId, ScheduleJob scheduleJob) throws ServiceException {

        jobDao.save(scheduleJob);

        ScheduleUtil.updateScheduleJob(scheduler, scheduleJob);

        return scheduleJob;
    }

    @Transactional(rollbackFor = DataAccessException.class)
    public boolean add(ScheduleJob scheduleJob) throws ServiceException {
        jobDao.save(scheduleJob);

        ScheduleUtil.createScheduleJob(scheduler, scheduleJob);

        return true;
    }

    @Transactional(rollbackFor = DataAccessException.class)
    public boolean delete(Long jobId) throws ServiceException {
       ScheduleJob scheduleJob = select(jobId);

       jobDao.deleteById(jobId);
      
        ScheduleUtil.deleteJob(scheduler, scheduleJob);

        return true;
    }

    public List<ScheduleJob> getAllJob() {
        return jobDao.getAllJob();
    }

    public boolean resume(Long jobId) throws ServiceException {
        ScheduleJob scheduleJob = updateScheduleJobStatus(jobId, false);
        ScheduleUtil.resumeJob(scheduler, scheduleJob);
        return true;
    }

    public boolean pause(Long jobId) throws ServiceException {
        ScheduleJob scheduleJob = updateScheduleJobStatus(jobId, true);
        ScheduleUtil.pauseJob(scheduler, scheduleJob);
        return true;
    }

    public boolean run(Long jobId) throws ServiceException {
        ScheduleJob scheduleJob = updateScheduleJobStatus(jobId, false);
        ScheduleUtil.run(scheduler, scheduleJob);
        return true;
    }


    private ScheduleJob updateScheduleJobStatus(Long jobId, Boolean isPause) throws ServiceException {
        ScheduleJob scheduleJob = select(jobId);
        scheduleJob.setPause(isPause);
        update(scheduleJob.getId(), scheduleJob);
        return scheduleJob;
    }
}
