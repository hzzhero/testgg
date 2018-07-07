package com.example.demo.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TestRepository;
import com.example.demo.entity.Test;
import com.example.demo.service.TestService;

@Service
public class TestServiceImpl implements TestService,Serializable {

	@Autowired
	private TestRepository testRepository;
	
	@Override
	public List<Test> findAll() {
		return testRepository.findAll();
	}

	@Override
	public void save() {
		Test t=new Test();
		t.setId(new Date().getTime()+"");
		t.setName(new Date().getTime()+"");
		t.setNum(new Date().getTime()+0.0);
		t.setTime(new Date());
		testRepository.save(t);
	}

}
