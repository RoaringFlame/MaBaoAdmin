package com.mabao.admin.config;

import com.mabao.admin.config.RootConfig.WebPackage;
import com.mabao.admin.util.aop.HistoryLog;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.util.regex.Pattern;

@Configuration
@Import(SpringDataJpaConfig.class)
@EnableAspectJAutoProxy
@ComponentScan(basePackages={"com.mabao.admin"},
    excludeFilters={
        @Filter(type=FilterType.CUSTOM, value=WebPackage.class)
    })
public class RootConfig {
  public static class WebPackage extends RegexPatternTypeFilter {
    public WebPackage() {
      super(Pattern.compile("com\\.pojo"));
    }    
  }

  @Bean
  public HistoryLog historyLog() {
    return new HistoryLog();
  }
}
