package com.example.demo.actuator;

import java.util.List;

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
public class Feature {

	private String featureName;
	private String featureDescription;
	private List <Function> functions;
}
