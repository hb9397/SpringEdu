package hello.coreex.order;

import hello.coreex.discount.DiscountPolicy;
/*import hello.coreex.discount.FixDiscountPolicy;
import hello.coreex.discount.RateDiscountPolicy;*/
import hello.coreex.member.Member;
import hello.coreex.member.MemberRepository;
import hello.coreex.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
	private final MemberRepository memberRepository = new MemoryMemberRepository();

	/*
		아래의 두 코드를 변경하는 것처럼 할인 정책을 바꾸는 것은 객체지향 원리를 잘 준수 한 것처럼 보이지만
		OrderServiceImpl 은 결국 추상 클래스인 인터페이스 뿐만 아니라 구체 클래스인 정률/고정의 할인정책 구체 클래스에 의존하는 것으로 DIP를 위반하게 되며
		두 할인 정책을 변경할 때 결국 OrderServiceImpl을 수정해야 하기 때문에 OCP를 위반하게 된다.
	*/

	//private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
	//private final RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

	private DiscountPolicy discountPolicy;
	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {

		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}
}
