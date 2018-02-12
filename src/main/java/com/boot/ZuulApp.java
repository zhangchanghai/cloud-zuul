package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulApp {
	@Bean
	public PatternServiceRouteMapper serviceRouteMapper() {
		return new PatternServiceRouteMapper("cloud-(?<name>.+$)", "${name}");
	}

	public static void main(String[] args) {
		SpringApplication.run(ZuulApp.class, args);
		System.out.println("====cloud-zuul 启动完毕====");
	}
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer(){
	       return new EmbeddedServletContainerCustomizer() {
	           @Override
	           public void customize(ConfigurableEmbeddedServletContainer container) {
	        	   ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/errorinterception");
	               ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/errorinterception");
	               ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errorinterception");

	               container.addErrorPages(error401Page, error404Page, error500Page);
	               container.setSessionTimeout(1800);//单位为S
	          }
	           
	           
	    };
	}
	
	
}
