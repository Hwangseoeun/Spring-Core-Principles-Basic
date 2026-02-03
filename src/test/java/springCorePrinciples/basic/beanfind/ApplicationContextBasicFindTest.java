package springCorePrinciples.basic.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springCorePrinciples.basic.AppConfig;
import springCorePrinciples.basic.member.MemberService;
import springCorePrinciples.basic.member.MemberServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

    final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @DisplayName("빈 이름으로 조회")
    @Test
    void findBeanByName() {
        final MemberService memberService = ac.getBean("memberService", MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @DisplayName("이름 없이 타입으로만 조회")
    @Test
    void findBeanByType() {
        final MemberService memberService = ac.getBean(MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 구체 타입으로 조회 시 유연성이 떨어짐
    @DisplayName("구체 타입으로 조회")
    @Test
    void findBeanByName2() {
        final MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @DisplayName("빈 이름으로 조회X")
    @Test
    void findBeanByNameX() {
//        final MemberService memberService = ac.getBean("xxx", MemberService.class);

        assertThrows(NoSuchBeanDefinitionException.class,
            () -> ac.getBean("xxx", MemberService.class)
        );
    }
}
