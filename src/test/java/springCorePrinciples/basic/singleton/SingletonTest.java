package springCorePrinciples.basic.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springCorePrinciples.basic.AppConfig;
import springCorePrinciples.basic.member.MemberService;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    @Test
    void pureContainer() {
        final AppConfig appConfig = new AppConfig();

        // 1. 조회: 호출할 때마다 객체를 생성
        final MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출할 때마다 객체를 생성
        final MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    @Test
    void singletonServiceTest() {
        final SingletonService singletonService1 = SingletonService.getInstance();
        final SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
    }

    @DisplayName("스프링 컨테이너와 싱글톤")
    @Test
    void springContainer() {
        final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        final MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        final MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
