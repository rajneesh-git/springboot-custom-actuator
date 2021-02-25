package com.example.demo.actuator;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.beans.BeansException;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.example.demo.annotations.Feature;
import com.example.demo.annotations.Function;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Endpoint(id="bcm")
public class BcmReportEndpoint implements ApplicationContextAware{

	private ApplicationContext context;
 
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

	
	@ReadOperation
	public Map<String, BCM> businessCapabilityMatrix() {
		return extract(this.context);
	}

	
	
	private void extractBcmDetailsFromPom(BCM bcm) {
		
		  MavenXpp3Reader reader = new MavenXpp3Reader();
	        Model model;
			try {
				model = reader.read(new FileReader("pom.xml"));
				 System.out.println(model.getId());
			        System.out.println(model.getGroupId());
			        System.out.println(model.getArtifactId());
			        System.out.println(model.getVersion());
			        Properties props = model.getProperties();
			        bcm.setName(props.getProperty("capability.name"));
			        bcm.setDescription(props.getProperty("capability.description"));
			
			} catch (IOException | XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
	        
	}
	
	
	private Map<String, BCM> extract(ApplicationContext context) {
		 
		Map<String, BCM> businesCapability  = new HashMap<>();
		
		
		ApplicationContext applicationContext = context;
		Map<String, List<com.example.demo.actuator.Feature>> featureDefs = new LinkedHashMap<>();
		
		applicationContext.getBeansWithAnnotation(Feature.class)
		.forEach((beanName, bean) -> featureDefs.put(beanName, describeFeatures(applicationContext, beanName)));
		
		BCM bcm = BCM.builder()
				.name("TBD")
				.description("TBD Description")
				.featureDefs(featureDefs)
				.build();
		
		extractBcmDetailsFromPom(bcm);
		
		businesCapability.put("Capability",  bcm);
		return businesCapability;
	}

	
	private List<com.example.demo.actuator.Feature>describeFeatures(ApplicationContext applicationContext,String beanName) {
		System.out.println("hello"+beanName);
		
		Feature fetAnno = applicationContext.findAnnotationOnBean(beanName, Feature.class);
		//AnnotationUtils.
		Map <String, Object> featureAnnoAtts = AnnotationUtils.getAnnotationAttributes(fetAnno);
		System.out.println("featureAnnoAtts"+featureAnnoAtts);
		List<com.example.demo.actuator.Feature> features = new ArrayList<>();
		if(featureAnnoAtts != null) {
			
			features.add(
						com.example.demo.actuator.Feature.builder()
						.featureName(featureAnnoAtts.get("id").toString())
						.featureDescription(featureAnnoAtts.get("about").toString())
						.build());
		}
		
		Method[] methods = applicationContext.getBean(beanName).getClass().getDeclaredMethods();
		List<com.example.demo.actuator.Function> funcs = new ArrayList<>();
		for (Method method : methods) {
			Function funAnno = method.getAnnotation(Function.class);
			if(funAnno != null) {		
			Map <String, Object> funcAnnoAtts = AnnotationUtils.getAnnotationAttributes(funAnno);
			System.out.println("funcAnnoAtts"+funcAnnoAtts);
			if(funcAnnoAtts != null) {
				
				funcs.add(
						com.example.demo.actuator.Function.builder()
						.name(funcAnnoAtts.get("id").toString())
						.description(method.getName()+"-" +funcAnnoAtts.get("about").toString())
						.build());
			
			}
			
			}
		}
		
		/*
		 * add all functions to feature 
		 * assumption is there is 1 feature per class
		 * and methods are functions 
		 * and 1feature => M functions
		 */
		
		features.get(0).setFunctions(funcs);
		
			return features;
	}
	


	private Map<String, Object> safeSerialize(ObjectMapper mapper, Object bean, String prefix) {
		try {
			return new HashMap<>(mapper.convertValue(bean, Map.class));
		}
		catch (Exception ex) {
			return new HashMap<>(Collections.singletonMap("error", "Cannot serialize '" + prefix + "'"));
		}
	}
	
}
