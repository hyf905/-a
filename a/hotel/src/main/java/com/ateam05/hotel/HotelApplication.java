package com.ateam05.hotel;

import com.ateam05.hotel.common.utils.VerifyServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;


@MapperScan("com.ateam05.hotel.mapper")
@SpringBootApplication
//@ServletComponentScan(basePackages = {"com.ateam05.hotel.filter"})
public class HotelApplication {


    public static void main(String[] args) {
        SpringApplication.run(HotelApplication.class, args);
    }
    /**
     * 注入验证码servlet
     */
    @Bean
    public ServletRegistrationBean indexServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new VerifyServlet());
        registration.addUrlMappings("/getVerifyCode");
        return registration;
    }

}
