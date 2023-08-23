package hello.coreex.beanFind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.coreex.AppConfig;

public class ApplicationContextInfoTest {
	AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("모든 빈 출력하기")
	void findAllBean(){
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		// 위와 같이 이터레이터를 적용할 수 있다면, iter 입력으로 아래와 같은 반복문을 자동완성 해준다~!

		for (String beanDefinitionName : beanDefinitionNames){
			Object bean = applicationContext.getBean(beanDefinitionName);
			System.out.println("name=" + beanDefinitionName + " object=" + bean);
		}
	}

	@Test
	@DisplayName("애플리케이션 빈 출력하기")
	void findApplicationBean(){
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

		for (String beanDefinitionName : beanDefinitionNames){
			BeanDefinition beanDefinition = applicationContext.getBeanDefinition(beanDefinitionName);

			// Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
			// Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
			if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
				Object bean = applicationContext.getBean(beanDefinitionName);
				System.out.println("name= " + beanDefinitionName + " object=" + bean);
			}
		}

	}
}
