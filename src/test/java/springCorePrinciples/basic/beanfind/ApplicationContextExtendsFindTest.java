package springCorePrinciples.basic.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springCorePrinciples.basic.discount.DiscountPolicy;
import springCorePrinciples.basic.discount.FixDiscountPolicy;
import springCorePrinciples.basic.discount.RateDiscountPolicy;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    final AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @DisplayName("부모 타입으로 조회 시 자식이 둘 이상 있으면 중복 오류가 발생한다")
    @Test
    void findBeanByParentTypeDuplicate() {
//        final DiscountPolicy bean = ac.getBean(DiscountPolicy.class);

        assertThrows(NoUniqueBeanDefinitionException.class,
            () -> ac.getBean(DiscountPolicy.class));
    }

    @DisplayName("부모 타입으로 조회 시 자식이 둘 이상 있으면 빈 이름을 지정하면 된다")
    @Test
    void findBeanByParentTypeDuplicateBeanName() {
        final DiscountPolicy bean = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);

        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @DisplayName("특정 하위 타입으로 조회")
    @Test
    void findBeanBySubType() {
        final RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);

        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @DisplayName("부모 타입으로 모두 조회")
    @Test
    void findAllBeanByParentType() {
        final Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);

        assertThat(beansOfType).hasSize(2);

        for (String key : beansOfType.keySet()) {
            System.out.println("key: " + key + ", value: " + beansOfType.get(key));
        }
    }

    @DisplayName("부모 타입으로 모두 조회 - Object")
    @Test
    void findAllBeanByObjectType() {
        final Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);

        for (String key : beansOfType.keySet()) {
            System.out.println("key: " + key + ", value: " + beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
