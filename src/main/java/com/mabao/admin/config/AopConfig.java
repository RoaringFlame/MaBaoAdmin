package com.mabao.admin.config;

import com.mabao.admin.util.aop.HistoryLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.mabao.admin.util.aop")
public class AopConfig {
    @Bean
    public HistoryLog historyLog() {
        return new HistoryLog();
    }
}
