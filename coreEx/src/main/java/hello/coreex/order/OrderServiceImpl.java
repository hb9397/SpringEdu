package hello.coreex.order;

import hello.coreex.discount.DiscountPolicy;
/*import hello.coreex.discount.FixDiscountPolicy;
import hello.coreex.discount.RateDiscountPolicy;*/
import hello.coreex.member.Member;
import hello.coreex.member.MemberRepository;
import hello.coreex.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
	//private final MemberRepository memberRepository = new MemoryMemberRepository();

	/*
		아래의 두 코드를 변경하는 것처럼 할인 정책을 바꾸는 것은 객체지향 원리를 잘 준수 한 것처럼 보이지만
		OrderServiceImpl 은 결국 추상 클래스인 인터페이스 뿐만 아니라 구체 클래스인 정률/고정의 할인정책 구체 클래스에 의존하는 것으로 DIP를 위반하게 되며
		두 할인 정책을 변경할 때 결국 OrderServiceImpl을 수정해야 하기 때문에 OCP를 위반하게 된다.
	*/

	//private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
	//private final RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

	/*
		위와 같이 OCP, DIP를 위반하게 된 코드는 클라이언트 코드가 추상이 아닌 구체화에 의존하고 있기 때문이며, 이를 위해
		AppConfig와 아래의 생성자 주입을 사용해 클라이언트 코드는 추상만을 의존하도록 해결하고, AppConfig를 통해서 요구사항의
		변경 시점마다 클라이언트 코드의 변경이 생기지 않도록 하여 DIP를 해결.
	*/
	private final DiscountPolicy discountPolicy;
	private final MemberRepository memberRepository;

	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {

		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	// @Configuration 이 AppConfig 에서 MemberService 와 OrderService 가 계속해서 새로운 MemoryMemberRespository 를 생성하는 것과 같아 보이는데
	// 어떻게 싱글톤을 보장하는가에 대한 Test Code 를 위한 테스용 메서드

	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
