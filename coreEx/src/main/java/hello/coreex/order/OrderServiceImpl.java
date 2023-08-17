package hello.coreex.order;

import hello.coreex.discount.DiscountPolicy;
import hello.coreex.discount.FixDiscountPolicy;
import hello.coreex.member.Member;
import hello.coreex.member.MemberRepository;
import hello.coreex.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
	private final MemberRepository memberRepository = new MemoryMemberRepository();
	private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {

		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}
}
