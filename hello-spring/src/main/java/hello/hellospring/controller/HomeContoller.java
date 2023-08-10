package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeContoller {
	// index.html 은 localhost:8080/ 으로 자동을 매핑되지만
	// 정적파일 보다 SpringContainer 에 등록된 @Controller 에 "/" 경로에 매핑되는
	// Controller가 있다면 먼저 잡히므로 무시된다.
	@GetMapping("/")
	public String home() {
		return "home";
	}
}
