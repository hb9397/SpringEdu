package hello.coreex.member;

public class MemberApp {
	/*
		아래와 같이 순수한 자바 코드로 애플리케이션을 개발 할 수 있으며, 테스트가 가능하다는 예시이지만
		main 메서드로 테스트를 진행하는데는 한계점도 있으며, 바람직한 방법도 아니므로 JUnit 을 이용하자~
	*/
	public static void main(String[] args) {
		MemberService memberService = new MemberServiceImpl();
		Member member = new Member(1L, "memberA", Grade.VIP);
		memberService.join(member);

		Member findMember = memberService.findMember(1L);
		System.out.println("new member = " + member.getName());
		System.out.println("find Member = " + findMember.getName());
	}
}
