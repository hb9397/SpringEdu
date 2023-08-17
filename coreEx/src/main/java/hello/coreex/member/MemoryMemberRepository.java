package hello.coreex.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

	/*
		실무에서는 메모리 레포지토리로 테스트를 할 시에 HashMap을 사용하면 동시성 문제(여러 서비스에서 HashMap 작업) 때문에
		ConcurrnetHashMap을 사용하는 것이 바람직하다 ConcurrnetHashMap 은 HashMap 처럼 동시에 여러 쓰레드에서 읽을 수는
		있으나 쓰기 작업에 있어서는 하나가 작업중일 경우 나머지는 작업 할 수 없다는 특징이 있기 때문이다.
	*/
	private static Map<Long, Member> store = new HashMap<>();
	@Override
	public void save(Member member) {
		store.put(member.getId(), member);
	}

	@Override
	public Member findById(Long memberId) {
		return store.get(memberId);
	}
}
