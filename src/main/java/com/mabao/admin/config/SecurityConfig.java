package com.mabao.admin.config;

import com.mabao.admin.util.filter.MyCharacterEncodingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  SpringDataJpaConfig springDataJpaConfig;
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.addFilterBefore(new MyCharacterEncodingFilter(),ChannelProcessingFilter.class);
    http.csrf().disable()
        .formLogin().loginPage("/login").defaultSuccessUrl("/");
    http.logout().logoutSuccessUrl("/");
//    http.authorizeRequests().regexMatchers("/").authenticated()
//            .regexMatchers("/admin/.*").authenticated()
//            .regexMatchers("/role/.*").authenticated();
  }
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setMessageSource(messageSource());
    auth.authenticationProvider(provider);
    auth.jdbcAuthentication().dataSource(springDataJpaConfig.dataSource())
            .usersByUsernameQuery("select username,password,enable from t_admin where username=?")
    .authoritiesByUsernameQuery("select A.username,R.name as 'authority' from t_admin A, t_role R " +
            "where A.role_id = R.id and A.username=?");
  }

  @Bean
  public ReloadableResourceBundleMessageSource messageSource(){
    ReloadableResourceBundleMessageSource messageSource  = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("WEB-INF/loginMessages");
    return messageSource;
  }
}
