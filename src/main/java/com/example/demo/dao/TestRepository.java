package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, String>,JpaSpecificationExecutor<Test>{

}
