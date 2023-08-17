package hello.coreex.discount;

import hello.coreex.member.Grade;
import hello.coreex.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{
	private int discountFixAmount = 1000; // 1000 원 할인
	@Override
	public int discount(Member member, int price) {
		// Enum은 == 을 사용하는 것이 맞다.
		if (member.getGrade() == Grade.VIP){
			return discountFixAmount;
		} else {
			return 0;
		}
	}
}
