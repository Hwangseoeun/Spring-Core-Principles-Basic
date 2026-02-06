package springCorePrinciples.basic.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springCorePrinciples.basic.AppConfig;
import springCorePrinciples.basic.member.MemberRepository;
import springCorePrinciples.basic.member.MemberServiceImpl;
import springCorePrinciples.basic.order.OrderServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @DisplayName("AppConfig에서 공통된 인스턴스를 두번 이상 new 하는 경우 테스트")
    @Test
    void configurationTest() {
        final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        final MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        final OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        final MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        final MemberRepository memberRepository1 = memberService.getMemberRepository();
        final MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @DisplayName("AppConfig에 관한 정보 조회 테스트")
    @Test
    void configurationDeep() {
        final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        final AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}
