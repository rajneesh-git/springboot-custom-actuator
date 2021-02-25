package com.example.demo.actuator;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@AllArgsConstructor@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class BCM {
	private String name;
	private String description;
	Map<String, List<Feature>> featureDefs;	
}
	 
