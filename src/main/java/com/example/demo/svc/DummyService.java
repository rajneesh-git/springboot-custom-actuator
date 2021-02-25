package com.example.demo.svc;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Greetings;
import com.example.demo.annotations.Feature;
import com.example.demo.annotations.Function;

 @RestController
public class DummyService {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

 	@GetMapping("/dummy")
	public Greetings dummy(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greetings(counter.incrementAndGet(), String.format(template, name));
		
	}


 	@GetMapping("/dummy2")
	public Greetings dummy2(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greetings(0, String.format(template, name));
		
	}
	
 	public Greetings dummy3(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greetings(counter.incrementAndGet(), String.format(template, name));
		
	}
	

}
