package hello.coreex.scan;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.coreex.AutoAppConfig;
import hello.coreex.member.MemberService;

public class AutoAppConfigTest {
	@Test
	void basicScan(){
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class);
		MemberService memberService = applicationContext.getBean(MemberService.class);
		assertThat(memberService).isInstanceOf(MemberService.class);
	}
}
