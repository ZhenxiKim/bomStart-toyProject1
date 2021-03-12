package com.toby.exercise;

import com.toby.exercise.repository.JdbcTemplateMemberRepository;
import com.toby.exercise.repository.MemberRepository;

import com.toby.exercise.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;


    public SpringConfig(DataSource dataSource){
        this.dataSource=dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JdbcTemplateMemberRepository(dataSource);
        //return new MemoryMemberRepository();
    }
}
