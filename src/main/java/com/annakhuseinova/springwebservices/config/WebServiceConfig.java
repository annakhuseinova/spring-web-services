package com.annakhuseinova.springwebservices.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import static com.annakhuseinova.springwebservices.constants.Constants.NAMESPACE;

/**
 * @EnableWs - used with @Configuration to have Spring Web Services configuration imported. It enables SOAP Web
 * service features in the Spring Boot application
 * */
@Configuration
@EnableWs
public class WebServiceConfig {

    /**
     * ServletRegistrationBean is one of the ways to configure and register servlets in Spring Boot application
     * */
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context){
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
    }

    /**
     *
     * */

    @Bean(name = "courses")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema){
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("CoursePort");
        defaultWsdl11Definition.setTargetNamespace(NAMESPACE);
        defaultWsdl11Definition.setSchema(coursesSchema);
        defaultWsdl11Definition.setLocationUri("/ws");
        return defaultWsdl11Definition;
    }

    /**
     *
     * */
    @Bean
    public XsdSchema coursesSchema(){
        return new SimpleXsdSchema(new ClassPathResource("schema.xsd"));
    }


}
