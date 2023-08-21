package hello.coreex.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.coreex.AppConfig;

public class MemberServiceTest {

	MemberService memberService;

	@BeforeEach
	public void beforeEach(){
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();
	}

	@Test
	void join(){
		// given - 아래와 같이 주어지고,
		Member member = new Member(1L, "memberA", Grade.VIP);

		// when - 어떤 시점에 어떻게 동작했을 때,
		memberService.join(member);
		Member findMember = memberService.findMember(1L);

		// then - 이렇게 된다.
		Assertions.assertThat(member).isEqualTo(findMember);
	}
}
