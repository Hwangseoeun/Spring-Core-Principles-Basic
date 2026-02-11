package springCorePrinciples.basic.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springCorePrinciples.basic.discount.DiscountPolicy;
import springCorePrinciples.basic.member.Member;
import springCorePrinciples.basic.member.MemberRepository;

@Component
public class OrderServiceImpl implements OrderService {

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(final MemberRepository memberRepository, final DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Autowired
    public void init(final MemberRepository memberRepository, final DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(final Long memberId, final String itemName, final int itemPrice) {
        final Member member = memberRepository.findById(memberId);
        final int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
