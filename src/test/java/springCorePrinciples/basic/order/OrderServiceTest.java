package springCorePrinciples.basic.order;

import org.junit.jupiter.api.Test;
import springCorePrinciples.basic.member.Grade;
import springCorePrinciples.basic.member.Member;
import springCorePrinciples.basic.member.MemberService;
import springCorePrinciples.basic.member.MemberServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {

    final MemberService memberService = new MemberServiceImpl();
    final OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        final Long memberId = 1L;
        final Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        final Order order = orderService.createOrder(memberId, "itemA", 10000);

        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}