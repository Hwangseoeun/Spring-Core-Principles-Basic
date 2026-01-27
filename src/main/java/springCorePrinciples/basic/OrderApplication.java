package springCorePrinciples.basic;

import springCorePrinciples.basic.member.Grade;
import springCorePrinciples.basic.member.Member;
import springCorePrinciples.basic.member.MemberService;
import springCorePrinciples.basic.order.Order;
import springCorePrinciples.basic.order.OrderService;

public class OrderApplication {
    public static void main(String[] args) {
        final AppConfig appConfig = new AppConfig();
        final MemberService memberService = appConfig.memberService();
        final OrderService orderService = appConfig.orderService();

        final Long memberId = 1L;
        final Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        final Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
