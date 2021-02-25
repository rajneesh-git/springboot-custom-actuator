package com.example.demo.svc;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Greetings;
import com.example.demo.annotations.Feature;
import com.example.demo.annotations.Function;

@Feature(id = "Bye Bye feature", about = "Able to send Exit meessage to the caller")
@RestController
public class ByeByeService {
	
	private static final String template = "Bye, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Function(id="Get bye greeting with tracking", about = " greet bye with number of times greated with help of counter. if server restarts counter will reset.")
	@GetMapping("/bye")
	public Greetings bye(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greetings(counter.incrementAndGet(), String.format(template, name));
		
	}


	@Function(id="Get bye greeting without tracking", about = " greet bye with number of times greated with help of counter. if server restarts counter will reset.")
	@GetMapping("/bye2")
	public Greetings bye2(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greetings(0,String.format(template, name));
		
	}
	
	

	@GetMapping("/bye3")
	public Greetings bye3(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greetings(counter.incrementAndGet(), String.format(template, name));
		
	}


}
