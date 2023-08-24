package hello.coreex.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
	// 실무에서 싱글톤을 사용할 때 자주 범하는 문제를 테스트

	@Test
	void statefulServiceSingleton() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = applicationContext.getBean("statefulService", StatefulService.class);
		StatefulService statefulService2 = applicationContext.getBean("statefulService", StatefulService.class);

		// ThreadA : A 사용자가 10000원 주문
		statefulService1.order("userA", 10000);

		// ThreadB : B 사용자가 20000원 주문
		statefulService2.order("userB", 20000);

		// ThreadA: 사용자 A 주문 금액 조회
		int price = statefulService1.getPrice();

		// ThreadA: A 사용자는 10000원을 기대했지만, 이와 다르게 20000원 출력
		System.out.println("price = " + price);

		Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
	}

	static class TestConfig{
		@Bean
		public StatefulService statefulService(){
			return new StatefulService();
		}
	}
}
