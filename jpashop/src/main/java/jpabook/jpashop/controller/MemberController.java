package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        //Model ->
        model.addAttribute("memberForm", new MemberForm());
        // 컨트롤러로 넘어갈 때 이 데이터를 실어서 간다.
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        //@Valid 쓰면 @NotEmpty 와 같은 애노테이션의 기능을 활성화 시켜준다.
        // 도메인에 있는 멤버와, 화면에 핏한 멤버 전용 폼 구분지어 만들어서 사용하는게 좋다. 둘의 차이가 심하기 때문에 정제가 필요하다.
        //BindingResult 오류가 생기면 result에 담아서 실행한다.
        // result에 에러에 대한 메서드가 굉장히 많다.

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        // 실무적으로 복잡해지면 DTO로 변환해서 화면에 꼭 필요한 내용만 출력하는것을 권장
        // 여기선 엔티티를 안건드려도 되기 때문에 이렇게 한 것이다.
        // 엔티티는 항상 순수하게 유지해야 유지보수에 좋다.
        // API를 만들때는 엔티티를 절대 외부로 반환하면 안된다. 템플릿 엔진은 선택적으로 괜찮다.
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
