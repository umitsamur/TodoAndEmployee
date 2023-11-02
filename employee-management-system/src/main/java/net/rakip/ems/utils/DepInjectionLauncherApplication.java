package net.rakip.ems.utils;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
class YourBusinesClass {
	Dependency1 dependency1;
	Dependency2 dependency2;

	public YourBusinesClass(Dependency1 dependency1, Dependency2 dependency2) {
		this.dependency1 = dependency1;
		this.dependency2 = dependency2;
	}

//	
//	@Autowired
//	public void setDependency1(Dependency1 dependency1) {
//		this.dependency1 = dependency1;
//	}
//
//
//	@Autowired
//	public void setDependency2(Dependency2 dependency2) {
//		this.dependency2 = dependency2;
//	}

	public String toString() {
		return "dependency1: " + dependency1 + ", dependency2: " + dependency2;
	}

}

@Component
class Dependency1 {

}

@Component
class Dependency2 {

}

@Configuration
@ComponentScan
public class DepInjectionLauncherApplication {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				DepInjectionLauncherApplication.class)) {
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			System.out.println(context.getBean(YourBusinesClass.class));
		}
	}

}
