package hello.coreex.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.coreex.member.Grade;
import hello.coreex.member.Member;
import hello.coreex.member.MemberService;
import hello.coreex.member.MemberServiceImpl;

public class OrderServiceTest {
	MemberService memberService = new MemberServiceImpl();
	OrderService orderService = new OrderServiceImpl();

	@Test
	void createOrder(){
		long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP	);
		memberService.join(member);

		Order order = orderService.createOrder(memberId, "ItemA", 10000);
		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}
}
