package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ScheduleJob;

@Repository
public interface JobRepository extends JpaRepository<ScheduleJob, Long>{
//    ScheduleJob select(long id);
//
//    Integer update(ScheduleJob scheduleJob);
//
//    Integer insert(ScheduleJob scheduleJob);
//
//    Integer delete(Long productId);

	@Query(value="select * from schedule_job",nativeQuery=true)
    List<ScheduleJob> getAllJob();

	@Query(value="from ScheduleJob where enable=true")
    List<ScheduleJob> getAllEnableJob();
}
