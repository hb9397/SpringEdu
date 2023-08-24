package hello.coreex.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatelessServiceTest {
	@Test
	void statelessServiceSingleton() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(StatelessServiceTest.TestConfig.class);
		StatelessService statelessService1 = applicationContext.getBean("statelessService", StatelessService.class);
		StatelessService statelessService2 = applicationContext.getBean("statelessService", StatelessService.class);

		// ThreadA : A 사용자가 10000원 주문
		int userAPrice = statelessService1.order("userA", 10000);

		// ThreadB : B 사용자가 20000원 주문
		int userBPrice = statelessService2.order("userB", 20000);

		// ThreadA: A 사용자는 10000원을 기대했지만, 이와 다르게 20000원 출력
		System.out.println("userAPrice = " + userAPrice);
		System.out.println("userBPrice = " + userBPrice);

		Assertions.assertThat(userAPrice).isEqualTo(10000);
	}

	static class TestConfig{
		@Bean
		public StatelessService statelessService(){
			return new StatelessService();
		}
	}
}
