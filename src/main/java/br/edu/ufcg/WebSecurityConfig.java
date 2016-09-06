package br.edu.ufcg;

import br.edu.ufcg.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                .anyRequest()
                .authenticated()
                .and()
            .csrf()
                .disable();

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
							AuthorityUtils.createAuthorityList(user.getUserClass().toString()));
				} else {
					throw new UsernameNotFoundException("could not find the user '" + user.getEmail() + "'");
				}
			}
		};
	}
}
