package com.basic2.controller;

import com.basic2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/join")
    public String do_join() {
        return "member/join";
    }

    @GetMapping("/member/login")
    public String do_login() {
        return "member/login";
    }

    @GetMapping("/member/list")
    public String do_list() {
        return "member/list";
    }

}
