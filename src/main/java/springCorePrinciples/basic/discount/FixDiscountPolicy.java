package springCorePrinciples.basic.discount;

import org.springframework.stereotype.Component;
import springCorePrinciples.basic.member.Grade;
import springCorePrinciples.basic.member.Member;

@Component
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;

    @Override
    public int discount(final Member member, final int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }

        return 0;
    }
}
