package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ScheduleJob;
import com.example.demo.util.ServiceException;

public interface JobService {
	
	 public List<ScheduleJob> getAllEnableJob();
	 
	 public List<ScheduleJob> getAllJob();
	 
	 public ScheduleJob select(Long jobId) throws ServiceException;
	 
	 public ScheduleJob update(Long jobId, ScheduleJob scheduleJob) throws ServiceException;

	 public boolean add(ScheduleJob scheduleJob) throws ServiceException ;
	 
	 public boolean delete(Long jobId) throws ServiceException;
	 
	 public boolean resume(Long jobId) throws ServiceException;
	 
	 public boolean pause(Long jobId) throws ServiceException;
	 
	 public boolean run(Long jobId) throws ServiceException;
	 

}
