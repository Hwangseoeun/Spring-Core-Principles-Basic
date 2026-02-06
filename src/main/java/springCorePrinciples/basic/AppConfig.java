package springCorePrinciples.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springCorePrinciples.basic.discount.DiscountPolicy;
import springCorePrinciples.basic.discount.RateDiscountPolicy;
import springCorePrinciples.basic.member.MemberRepository;
import springCorePrinciples.basic.member.MemberService;
import springCorePrinciples.basic.member.MemberServiceImpl;
import springCorePrinciples.basic.member.MemoryMemberRepository;
import springCorePrinciples.basic.order.OrderService;
import springCorePrinciples.basic.order.OrderServiceImpl;

// @Configuration 어노테이션이 붙지 않으면 CGLIB 기술이 스프링 빈에 등록되는 것이 아닌, 순수한 AppConfig가 스프링 빈으로 등록됨
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
