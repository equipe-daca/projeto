package br.edu.ufcg;

import br.edu.ufcg.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
	protected void configure(HttpSecurity http) throws Exception {

		 http
             .httpBasic()
             .and()
                 .authorizeRequests()
                 /*USER*/
                 .antMatchers(HttpMethod.GET, "/user").hasAuthority("ROLE_ADMIN")
                 .antMatchers(HttpMethod.GET, "/user/*").hasAuthority("ROLE_ADMIN")
                 .antMatchers(HttpMethod.POST, "/user").hasAnyAuthority("ROLE_ADMIN", "ROLE_NORMAL")
                 .antMatchers(HttpMethod.PUT, "/user/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_NORMAL")
                 .antMatchers(HttpMethod.DELETE, "/user/*").hasAuthority("ROLE_ADMIN")
                 /*PROBLEM*/
                 .antMatchers(HttpMethod.POST, "/v1/problem").hasAnyAuthority("ROLE_ADMIN", "ROLE_NORMAL")
                 .antMatchers(HttpMethod.PUT, "/v1/problem/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_NORMAL")
                 .antMatchers(HttpMethod.DELETE, "/v1/problem/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_NORMAL")
                 /*TEST*/
                 .antMatchers(HttpMethod.POST, "/problem/*/teste").hasAnyAuthority("ROLE_ADMIN", "ROLE_NORMAL")
                 .antMatchers(HttpMethod.PUT, "/problem/*/teste/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_NORMAL")
                 .antMatchers(HttpMethod.DELETE, "/problem/*/teste/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_NORMAL")
                 /*SOLUTION*/
                 .antMatchers(HttpMethod.POST, "/solution").hasAnyAuthority("ROLE_ADMIN", "ROLE_NORMAL")
                 .antMatchers(HttpMethod.PUT, "/solution/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_NORMAL")
                 .antMatchers(HttpMethod.DELETE, "/solution/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_NORMAL")
             .and()
                .csrf().disable();

        http.csrf().disable();
	}

    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");*/
        auth.userDetailsService(userDetailsService());
	}

    @Bean
	protected UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Autowired
            UserRepository userRepository;

			@Override
			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                br.edu.ufcg.domain.User user = userRepository.findByEmail(email);
				if (user != null) {
					return new User(user.getEmail(), user.getPassword(), true, true, true, true,
							AuthorityUtils.createAuthorityList("ROLE_"+user.getUserClass()));
				} else {
					throw new UsernameNotFoundException("could not find the user '" + user.getEmail() + "'");
				}
			}
		};
	}
}
