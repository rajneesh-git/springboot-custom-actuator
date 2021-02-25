package com.example.demo.actuator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@ToString 
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Function {

	private String name;
	private String description;
}
