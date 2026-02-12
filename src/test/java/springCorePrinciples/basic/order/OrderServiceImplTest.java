package springCorePrinciples.basic.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import springCorePrinciples.basic.discount.FixDiscountPolicy;
import springCorePrinciples.basic.member.Grade;
import springCorePrinciples.basic.member.Member;
import springCorePrinciples.basic.member.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceImplTest {

    @DisplayName("OrderService 생성자 주입 테스트")
    @Test
    void createOrder() {
        final MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        memoryMemberRepository.save(new Member(1L, "name", Grade.VIP));

        final OrderServiceImpl orderService = new OrderServiceImpl(memoryMemberRepository, new FixDiscountPolicy());
        final Order order = orderService.createOrder(1L, "itemA", 10000);

        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}