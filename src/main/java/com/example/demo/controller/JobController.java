package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.common.CommonResponse;
import com.example.demo.common.ResponseUtil;
import com.example.demo.entity.ScheduleJob;
import com.example.demo.service.JobService;
import com.example.demo.util.ServiceException;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public CommonResponse getAllJob() {
        return ResponseUtil.generateResponse(jobService.getAllJob());
    }

    @GetMapping("/{id}")
    public CommonResponse getJob(@PathVariable("id") Long jobId) throws ServiceException {
        return ResponseUtil.generateResponse(jobService.select(jobId));
    }

    @PutMapping("/update/{id}")
    public CommonResponse updateJob(@PathVariable("id") Long jobId, @RequestBody ScheduleJob newScheduleJob) throws ServiceException {
        return ResponseUtil.generateResponse(jobService.update(jobId, newScheduleJob));
    }

    @DeleteMapping("/delete/{id}")
    public CommonResponse deleteJob(@PathVariable("id") Long jobId) throws ServiceException {
        return ResponseUtil.generateResponse(jobService.delete(jobId));
    }

    @PostMapping("/save")
    public CommonResponse saveJob(@RequestBody ScheduleJob newScheduleJob) throws ServiceException {
        return ResponseUtil.generateResponse(jobService.add(newScheduleJob));
    }


    @PutMapping("/run/{id}")
    public CommonResponse runJob(@PathVariable("id") Long jobId) throws ServiceException {
        return ResponseUtil.generateResponse(jobService.run(jobId));
    }


    @PutMapping("/pause/{id}")
    public CommonResponse pauseJob(@PathVariable("id") Long jobId) throws ServiceException {
        return ResponseUtil.generateResponse(jobService.pause(jobId));
    }

    @PutMapping("/resume/{id}")
    public CommonResponse resumeJob(@PathVariable("id") Long jobId) throws ServiceException {
        return ResponseUtil.generateResponse(jobService.resume(jobId));
    }

}
