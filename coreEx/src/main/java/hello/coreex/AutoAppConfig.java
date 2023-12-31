package hello.coreex;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// @Configuration 이 ComponentScan 의 대상이 되는 이유 또한 @Component 어노테이션이 @Configuration 를 정의한 클래스에 있기 때문

@Configuration
// 컴포넌트 스캔을 사용하면 @Configuration 이 붙은 설정 정보도 자동으로 등록되기 때문에,
// AppConfig, TestConfig 등 앞서 만들어두었던 설정 정보도 함께 등록되고, 실행되어 버린다. 그래서
// excludeFilters 를 이용해서 설정정보는 컴포넌트 스캔 대상에서 제외했다
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {

}
