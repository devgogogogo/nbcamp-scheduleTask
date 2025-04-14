//package hello.schedule2.config;
//
//import hello.schedule2.filter.LoginFilter;
//import jakarta.servlet.Filter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class WebConfig {
//
//    @Bean
//    public FilterRegistrationBean LoginFilter() {
//        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
//
//        // 필터 등록
//        filterRegistrationBean.setFilter(new LoginFilter());
//
//        //필서 순서 1
//        filterRegistrationBean.setOrder(1);
//
//        //전체 URL에 필터 적용
//        filterRegistrationBean.addUrlPatterns("/*");
//        return filterRegistrationBean;
//    }
//}
