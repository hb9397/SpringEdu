package hello.hellospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;

@Controller
public class MemberController {

	private final MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/members/new")
	public String CreateForm(){
		return "members/createMemberForm";
	}

	@PostMapping("/members/new")
	public String create(MemberForm form){
		Member member = new Member();
		member.setName(form.getName());

		memberService.join(member);

		return "redirect:/";
	}

	@GetMapping("/members")
	public String list(Model model){
		List<Member> members = memberService.findMembers();

		// model 로 보낸 members 데이터는 thymeleaf와 같은 템플릿 엔진이
		// "${members}" 와 같이 접근하고 th:each="member : ${members}" 와 같은 경우
		// member로 템플릿엔진에서 member 로 loop로 접근하는 것.
		// 이런식으로 템플릿 엔진에서 값을 꺼내서 쓰려면 보내는 데이터 형식에 Getter/Setter가 존재해야 한다.
		model.addAttribute("members", members);
		return "members/memberList";
	}
}
