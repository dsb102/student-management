package com.example.quanlisinhvien.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/mentor").setViewName("mentor/homeMentor");
        registry.addViewController("/admin").setViewName("admin/homeAdmin");
        registry.addViewController("/internship").setViewName("internship/homeInternship");
        registry.addViewController("/404").setViewName("/404");
    }
}
