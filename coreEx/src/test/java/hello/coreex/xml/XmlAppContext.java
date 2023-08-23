package hello.coreex.xml;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import hello.coreex.member.MemberService;

public class XmlAppContext {

	@Test
	void xmlAppContext(){
		ApplicationContext applicationContext = new GenericXmlApplicationContext("appConfig.xml");

		MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
		Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
	}
}
