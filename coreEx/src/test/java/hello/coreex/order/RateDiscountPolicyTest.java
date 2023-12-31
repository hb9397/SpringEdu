package hello.coreex.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.coreex.discount.RateDiscountPolicy;
import hello.coreex.member.Grade;
import hello.coreex.member.Member;

public class RateDiscountPolicyTest {
	RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

	@Test
	@DisplayName("VIP 는 10% 할인이 적용되어야 한다.")
	void vip_o(){
		//given
		Member member = new Member(1L, "memberA", Grade.VIP);
		//when
		int discount = rateDiscountPolicy.discount(member, 10000);
		//then
		Assertions.assertThat(discount).isEqualTo(1000);
	}

	@Test
	@DisplayName("VIP 가 안라면 할인이 적용되지 않아야 한다.")
	void vip_x(){
		//given
		Member member = new Member(2L, "memberB", Grade.BASIC);
		//when
		int discount = rateDiscountPolicy.discount(member, 10000);
		//then
		Assertions.assertThat(discount).isEqualTo(0);
	}
}
