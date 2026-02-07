package springCorePrinciples.basic.discount;

import org.springframework.stereotype.Component;
import springCorePrinciples.basic.member.Grade;
import springCorePrinciples.basic.member.Member;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(final Member member, final int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        }

        return 0;
    }
}
