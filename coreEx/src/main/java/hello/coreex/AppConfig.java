package hello.coreex;

import hello.coreex.member.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.coreex.discount.DiscountPolicy;
import hello.coreex.discount.FixDiscountPolicy;
import hello.coreex.discount.RateDiscountPolicy;
import hello.coreex.member.MemberService;
import hello.coreex.member.MemberServiceImpl;
import hello.coreex.member.MemoryMemberRepository;
import hello.coreex.order.OrderService;
import hello.coreex.order.OrderServiceImpl;

@Configuration
public class AppConfig {
	// PS -> 메서드 대체 단축키는 Ctrl + Alt + M 하면 가능 -> new 로 생성 하는 대신 새로운 생성자 생성해주면서 메서드 호출로 대체

	// @Bean memberService 호출 시 new MemoryMemberRepository()
	// @Bean orderService  호출 시 new MemoryMemberRepository()
	// @Configuration 은 싱글톤을 보장해 준다고 했는데, 코드만 보면 new 연산자를 통해서 계속해서 새로운 MemoryMemberRepository 객체를 생성하는 것과 같아 싱글톤을 위반 하는 것으로 보인다.
	// 과연 Spring Container 는 이 경우에 어떻게 싱글톤을 보장해 줄 것인가?

	// 출력 로그를 확인하면 결론적으로 @Configuration 은 아래와 같이 new 연산자로 같은 클래스 객체를 여러번 호출하여도 Bean 으로써 SpringContainer 에 등록된 상태이기 때문에
	// 싱글톤을 보장해주어 각 1개씩의 객체만을 생성하는 것을 알 수 있다.
	@Bean
	public MemberService memberService(){
		// 1번
		System.out.println("call AppConfig.memberService");
		return new MemberServiceImpl(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		// 1번
		System.out.println("call AppConfig.memberRepository");
		return new MemoryMemberRepository();
	}

	@Bean
	public OrderService orderService(){
		// 1번
		System.out.println("call Appconfig.orderService");
		return new OrderServiceImpl(
			memberRepository(),
			discountPolicy()
		);
	}

	@Bean
	private static DiscountPolicy discountPolicy() {
		//return new FixDiscountPolicy();
		return new RateDiscountPolicy();
	}
}
