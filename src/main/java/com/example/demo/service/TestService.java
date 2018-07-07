package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Test;

public interface TestService {
	
	public List<Test> findAll();

	public void save() ;
}
