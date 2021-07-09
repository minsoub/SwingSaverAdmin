package kr.co.swingsaver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import kr.co.swingsaver.security.JwtAuthenticationEntryPoint;
import kr.co.swingsaver.security.JwtRequestFilter;
import kr.co.swingsaver.service.AuthDetailsService;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{

	private static final String[] AUTH_WHITELIST = {
			// -- swagger ui
			"/v2/api-docs",
			"/configuration/ui",
			"/swagger-resources/**",
			"/configuration/security",
			"/swagger-ui.html",
			"/webjars/**",
			"/swagger/**",
			"/swagger-ui/**",
			"/v3/api-docs/**",
			// other public endpoints of your API may be appended to this array
			"/h2/**"
	};
	

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private AuthDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity  //.csrf().disable() //.disable()
				.authorizeRequests()
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll() 
				.antMatchers("/api/auth/**").permitAll()
				.antMatchers("/actuator/health").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.anyRequest().authenticated().and()		
				.csrf().disable().cors().and()
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()				
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(AUTH_WHITELIST);
	}	
	
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
       CorsConfiguration configuration = new CorsConfiguration();
       // - (3)
       configuration.addAllowedOrigin("http://localhost:4200");
       configuration.addAllowedOrigin("http://localhost:8080");
       //configuration.addAllowedOriginPattern("*/api/**");
       configuration.addAllowedMethod("*");
       configuration.addAllowedHeader("*");
       configuration.setAllowCredentials(true);
       configuration.setMaxAge(3600L);
       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       source.registerCorsConfiguration("/**", configuration);
       return source;
   }	
}
