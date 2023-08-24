package hello.coreex.singleton;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.coreex.AppConfig;
import hello.coreex.member.MemberRepository;
import hello.coreex.member.MemberServiceImpl;
import hello.coreex.member.MemoryMemberRepository;
import hello.coreex.order.OrderServiceImpl;

public class ConfigurationSingletonTest {

	@Test
	void configurationTest(){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberServiceImpl memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = applicationContext.getBean("orderService", OrderServiceImpl.class);
		MemberRepository memberRepository = applicationContext.getBean("memberRepository", MemberRepository.class);

		// 모두 같은 인스턴스를 참고 하고 있어야 한다.
		System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
		System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());
		System.out.println("memberRepository = " + memberRepository);

		// 모두 같은 인스턴스를 참조하는지 테스트
		assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
		assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
	}
}
