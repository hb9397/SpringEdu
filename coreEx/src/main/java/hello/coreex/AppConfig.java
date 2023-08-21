package hello.coreex;

import hello.coreex.discount.FixDiscountPolicy;
import hello.coreex.member.MemberService;
import hello.coreex.member.MemberServiceImpl;
import hello.coreex.member.MemoryMemberRepository;
import hello.coreex.order.OrderService;
import hello.coreex.order.OrderServiceImpl;

public class AppConfig {
	public MemberService memberService(){
		return new MemberServiceImpl(new MemoryMemberRepository());
	}

	public OrderService orderService(){
		return new OrderServiceImpl(
			new MemoryMemberRepository(),
			new FixDiscountPolicy()
		);
	}
}
