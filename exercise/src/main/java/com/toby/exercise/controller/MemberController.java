package com.toby.exercise.controller;

import com.toby.exercise.domain.Member;
import com.toby.exercise.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) throws SQLException{
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) throws SQLException{
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

    @GetMapping ("/members/delete")
    public String delete(@RequestParam("id") String id) throws SQLException{

        memberService.deleteMember(id);
        return "redirect:/members";
    }

    @GetMapping ("/members/update")
    public String update(@RequestParam("id") String id, Model model) throws SQLException{

        model.addAttribute("id",id);
        return "members/updateMemberForm";
    }

    @PostMapping ("/members/update")
    public String update(MemberForm form) throws SQLException{
        Member member = new Member();
        member.setName(form.getName());
        member.setId(form.getId());

        memberService.updateMember(member);

        return "redirect:/";
    }

}
