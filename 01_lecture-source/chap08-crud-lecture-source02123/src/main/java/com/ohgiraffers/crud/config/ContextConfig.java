package com.ohgiraffers.crud.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

// Application 의 위치가 config 폴더 내부로 오면서 BeanScan 범위가 줄어들게 됨
@Configuration
@ComponentScan(basePackages = "com.ohgiraffers.crud")
@MapperScan(basePackages = "com.ohgiraffers.crud", annotationClass = Mapper.class)
public class ContextConfig {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        /* comment
        *   message.properties 파일을 자바 객체 형식으로
        *   읽어들일 수 있게 만든다.
        *  */
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        // classpath: -> src/main/resource, src/main/java 를 의미한다.
        source.setBasename("classpath:/messages/message");
        source.setDefaultEncoding("UTF-8");
        /* 제공하지 않는 언어로 요청 시에 사용할 메세지 설정 */
        Locale.setDefault(Locale.KOREA);

        return source;
    }

}
