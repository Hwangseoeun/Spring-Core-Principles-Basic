package springCorePrinciples.basic.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    @DisplayName("필드를 가지고 있어 상태성인 경우")
    @Test
    void statefulServiceSingleton() {
        final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        final StatefulService statefulService1 = ac.getBean(StatefulService.class);
        final StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A 사용자가 10,000원 주문
        statefulService1.order("userA", 10000);

        // ThreadA: B 사용자가 20,000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA: 사용자가 A가 주문 금액 조회
        final int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}