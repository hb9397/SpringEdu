package hello.coreex.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

	//private final MemberRepository memberRepository = new MemoryMemberRepository();

	/*
		기존의 위의 코드는 클라이언트 코드가 구현에 의존하는 형태의 코드로, AppConfig와 아래의 코드와 같이 작성하여
		클라이언트 코드가 구현이 아닌 추상에만 의존하도록 생성자 주입을 사용한다.
	*/
	private final MemberRepository memberRepository;

	@Autowired // --> applicationContext.getBean(MemberRepository.class) 와 유사한 역할
	// ComponentScan 사용 이전에 AppConfig 에서는 @Bean 으로 직접 설정 정보를 작성했고, 의존관계도 직접 명시했다.
	// 이제는 이런 설정 정보 자체가 없기 때문에, 의존관계 주입도 이 클래스 안에서 해결해야 한다.
	// @Autowired 는 의존관계를 자동으로 주입해준다.
	public MemberServiceImpl(MemberRepository memberRepository){
		this.memberRepository = memberRepository;
	}
	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}

	//테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
