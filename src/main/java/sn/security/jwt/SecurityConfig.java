package sn.security.jwt;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "sn.security")

public class SecurityConfig extends WebSecurityConfigurerAdapter{
     @Autowired
     private UserDetailsService userDetailsService;
     @Autowired
     private BCryptPasswordEncoder passwordEncoder;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
	}
	
//	@Autowired
//	public void configureGlobal(
//	AuthenticationManagerBuilder auth,DataSource dataSource) throws Exception
//	{
//		 auth.inMemoryAuthentication()
//         .withUser("user")
//         .password("{noop}passer")
//         .roles("USER")
//         .and()
//         .withUser("sysuser")
//         .password("{noop}password")
//         .roles("ADMIN");
//	
////	auth.jdbcAuthentication()
////	.dataSource((javax.sql.DataSource) dataSource)
////	.usersByUsernameQuery("select username as principal ,password as credentials , true from utilisateur where username = ?")
////	.authoritiesByUsernameQuery("select Utilisateur_username as principal ,	roles_role as role from utilisateur_roles where  Utilisateur_username = ? ")
////	.rolePrefix("ROLE_");
//	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/login/**").permitAll();
		http.authorizeRequests().antMatchers("/css/**","/js/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/tasks/**").hasAuthority("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
	
	//Formatters

   

}
