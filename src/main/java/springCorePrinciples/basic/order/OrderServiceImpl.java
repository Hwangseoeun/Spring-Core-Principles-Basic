package springCorePrinciples.basic.order;

import springCorePrinciples.basic.discount.DiscountPolicy;
import springCorePrinciples.basic.discount.FixDiscountPolicy;
import springCorePrinciples.basic.member.Member;
import springCorePrinciples.basic.member.MemberRepository;
import springCorePrinciples.basic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(final Long memberId, final String itemName, final int itemPrice) {
        final Member member = memberRepository.findById(memberId);
        final int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
