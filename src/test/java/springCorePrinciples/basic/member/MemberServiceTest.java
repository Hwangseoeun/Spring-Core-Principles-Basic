package springCorePrinciples.basic.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springCorePrinciples.basic.AppConfig;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        final AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given
        final Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        final Member findMember = memberService.findMember(1L);

        //then
        assertThat(member).isEqualTo(findMember);
    }
}