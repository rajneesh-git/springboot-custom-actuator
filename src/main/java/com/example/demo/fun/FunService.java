package com.example.demo.fun;

import org.springframework.stereotype.Component;

import com.example.demo.annotations.Feature;
import com.example.demo.annotations.Function;


@Component
@Feature(id="GetFun Feature", about = "Get fun from annotation feature")
public class FunService {

	private String funText = "Fun with nnotations";
	
	@Function(id="just for funfunction", about = "Get fun from annotation func")
	public String  getFun() {
		return funText;
		
	}
	
}
