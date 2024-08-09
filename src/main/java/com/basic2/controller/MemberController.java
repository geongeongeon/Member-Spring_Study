package com.basic2.controller;

import com.basic2.MemberForm;
import com.basic2.model.Member;
import com.basic2.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/login")
    public String show_login() {
        return "member/login";
    }

    @PostMapping("/member/login/submit")
    public String do_login(MemberForm form, HttpSession session) {
        Optional<Member> member = memberService.findByName(form.getName());

        if (!member.isPresent()) {
            System.out.println("존재하지 않는 회원입니다.");
            return "redirect:/member/login?error=not-found";
        }

        if (!member.get().getPassword().equals(form.getPassword())) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return "redirect:/member/login?error=incorrect-password";
        }

        session.setAttribute("loginMember", member.get());

        System.out.println(form.getName() + "님 로그인 되었습니다.");
        return "redirect:/";
    }

    @GetMapping("/member/join")
    public String show_join() {
        return "member/join";
    }

    @PostMapping("/member/join/submit")
    public String do_join(MemberForm form) {
        if (form.getName().isEmpty() || form.getPassword().isEmpty()) {
            System.out.println("이름과 비밀번호를 모두 입력해주세요.");
            return "redirect:/member/join?error=missing-fields";
        }

        Optional<Member> member = memberService.findByName(form.getName());
        if (member.isPresent()) {
            System.out.println("이미 존재하는 회원입니다.");
            return "redirect:/member/join?error=duplicate-name";
        }

        Member member1 = new Member();
        member1.setName(form.getName());
        member1.setPassword(form.getPassword());

        memberService.join(member1);

        System.out.println(form.getName() + "님 가입이 완료되었습니다.");
        return "redirect:/";
    }

    @GetMapping("/member/list")
    public String show_list(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);

        for (Member m : members) System.out.println(m);
        return "member/list";
    }

    @GetMapping("/member/logout")
    public String do_logout(HttpSession session) {
        session.invalidate();

        System.out.println("로그아웃 되었습니다.");
        return "redirect:/";
    }

}
