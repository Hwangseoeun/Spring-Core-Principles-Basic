package springCorePrinciples.basic.discount;

import springCorePrinciples.basic.member.Member;

public interface DiscountPolicy {

    // return : 할인 대상 금액
    int discount(final Member member, int price);
}
