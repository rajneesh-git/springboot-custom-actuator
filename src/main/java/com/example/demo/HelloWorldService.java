package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotations.Feature;
import com.example.demo.annotations.Function;

@Feature(id = "Able to greet the caller", about = "This feature serves the greating.")
@RestController
public class HelloWorldService {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Function(id="Get greeting with tracking", about = " greet with number of times greated with help of counter. if server restarts counter will reset.")
	@GetMapping("/greeting")
	public Greetings greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greetings(counter.incrementAndGet(), String.format(template, name));
		
	}


	@Function(id="Get greeting without tracking", about = " greet with number of times greated with help of counter. if server restarts counter will reset.")
	@GetMapping("/greeting2")
	public Greetings greeting2(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greetings(0, String.format(template, name));
		
	}
	
	@GetMapping("/greeting3")
	public Greetings greeting3(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greetings(counter.incrementAndGet(), String.format(template, name));
		
	}
	

}
