package springCorePrinciples.basic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


// ComponentScan 내부에 basePackages와 같은 탐색할 패키지의 시작 위치를 별도로 지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 됨
@Configuration
@ComponentScan(
//    basePackages = "springCorePrinciples.basic.member",
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
