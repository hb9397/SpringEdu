package hello.coreex;

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
	@Bean
	public MemberService memberService(){
		return new MemberServiceImpl(memberRepository());
	}

	@Bean
	public static MemoryMemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}

	@Bean
	public OrderService orderService(){
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
