package com.manageplat;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.servlet.MultipartConfigElement;

/**
 *
 * @date 2017/5/16
 * @desc
 */
@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(value = "com.manageplat.*.dao", repositoryBaseClass =cn.gfire.base.jpa.dao.impl.BaseRepositoryImpl.class)
@EnableJpaAuditing
@EntityScan(value = {"com.manageplat.*.domain"})
public class Application {

//    /**
//     * spring -data 的审计
//     * @return
//     */
//    @Bean
//    public AuditorAware<AdminUser> auditorProvider() {
//        return new AdminUserAudit();
//    }


//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        super.addArgumentResolvers(argumentResolvers);
//        argumentResolvers.add(new AnnexHandlerMethodArgumentResolver());
//        argumentResolvers.add(new UserHandlerMethodArgumentResolver());
//    }

//    @Bean
//    public CommonsMultipartResolver multipartResolver(){
//        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//        commonsMultipartResolver.setMaxInMemorySize(104857600);
//        commonsMultipartResolver.setMaxInMemorySize(4096);
//        return commonsMultipartResolver;
//    }
        @Bean
        public MultipartConfigElement multipartConfigElement() {
            MultipartConfigFactory factory = new MultipartConfigFactory();
            //50M
            factory.setMaxFileSize(300*1024L * 1024L);
            return factory.createMultipartConfig();
        }


    //spring boot 的校验
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        return new MethodValidationPostProcessor();
    }

    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

//    @Bean
//    public Connector httpConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        //Connector监听的http的端口号
//        connector.setPort(80);
//        connector.setSecure(false);
//        //监听到http的端口号后转向到的https的端口号
//        connector.setRedirectPort(8090);
//        return connector;
//    }

//

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
