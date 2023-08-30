package hello.coreex.member;

public class MemberServiceImpl implements MemberService{

	//private final MemberRepository memberRepository = new MemoryMemberRepository();

	/*
		기존의 위의 코드는 클라이언트 코드가 구현에 의존하는 형태의 코드로, AppConfig와 아래의 코드와 같이 작성하여
		클라이언트 코드가 구현이 아닌 추상에만 의존하도록 생성자 주입을 사용한다.
	*/
	private final MemberRepository memberRepository;
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

	// @Configuration 이 AppConfig 에서 MemberService 와 OrderService 가 계속해서 새로운 MemoryMemberRespository 를 생성하는 것과 같아 보이는데
	// 어떻게 싱글톤을 보장하는가에 대한 Test Code 를 위한 테스용 메서드
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
