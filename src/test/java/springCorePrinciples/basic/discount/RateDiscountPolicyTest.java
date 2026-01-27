package springCorePrinciples.basic.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import springCorePrinciples.basic.member.Grade;
import springCorePrinciples.basic.member.Member;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    final RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    @Test
    void discount_vip_o() {
        //given
        final Member member = new Member(1L, "memberVIP", Grade.VIP);

        //when
        final int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(1000);
    }

    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    @Test
    void discount_vip_x() {
        //given
        final Member member = new Member(2L, "memberBASIC", Grade.BASIC);

        //when
        final int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(0);
    }
}