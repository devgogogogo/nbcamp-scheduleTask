package hello.schedule2.config;

import hello.schedule2.filter.CustomFilter;
import hello.schedule2.filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean customFilter() {

        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        //필터 등록
        filterRegistrationBean.setFilter(new CustomFilter());

        //필터 순서 설정
        filterRegistrationBean.setOrder(1);

        // 전체 URL에 필터 적용
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        //필터 등록
        filterRegistrationBean.setFilter(new LoginFilter());

        //필터 순서 설정
        filterRegistrationBean.setOrder(2);

        //전체 URL에 필터 적용
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }


}
