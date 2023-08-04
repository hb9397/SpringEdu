package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@GetMapping("hello")
	public String hello(Model model){
		model.addAttribute("data", "hello!!");
		return "hello";
	}

	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam("name") String name, Model model){
		model.addAttribute("name", name);
		return "hello-template";
	}

	@GetMapping("hello-string")
	// HTTP 통신 응답 Body 부에 해당 데이터를 반환하겠다는 의미
	// return 을 HTML 로 만들면 화면을 구성할 수 있기도 하다.
	@ResponseBody
	public String helloString(@RequestParam("name") String name){
		return "hello " + name;
	}

	// api 를 사용하는 이유 - 문자열이 아닌 데이터를 응답할 때
	static class Hello{
		// Hello 객체의 name 속성은 private로 잠겨 있으나 Getter, Setter 로 public으로 접근할 수 있도록 하는 방식
		// Java Bean 표준 방식이며, Property 접근 방식이라고도 한다.
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	// 문자열이 아닌 데이터를 ResponseBody로 API 방식을 사용해 반환하였을 때, JSON 형태의 데이터로 반환한다.
	// 지금은 RestController로 해결할 수 있는거 아닐까,.,.?
	@GetMapping("hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam("name") String name){
		Hello hello = new Hello();
		hello.setName(name);
		return hello;
	}
}
