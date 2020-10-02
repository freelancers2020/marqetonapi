package com.marqeton.marqetonapi.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.marqeton.marqetonapi.constants.RestUrlConstants;


@EnableWebSecurity
public class SecurityConfiguration  {


	@Autowired
	@Qualifier("customer")
	UserDetailsService userDetailsServiceImpl;

	@Autowired
	@Qualifier("admin")
	UserDetailsService adminDetailsServiceImpl;



	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addExposedHeader("Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
	            "Content-Type, Access-Control-Request-Method, Custom-Filter-Header");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("HEAD");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}


	public SecurityConfiguration() {

	}

	/*public SecurityConfiguration(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}*/

	

	@Configuration
	@Order(1)
	public class SecurityConfiguration1 extends WebSecurityConfigurerAdapter { 

		@Bean("CUST")
		public CorsFilter corsFilter() {
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowCredentials(true);
			config.addAllowedOrigin("*");
			config.addAllowedHeader("*");
			config.addAllowedMethod("OPTIONS");
			config.addAllowedMethod("GET");
			config.addAllowedMethod("POST");
			config.addAllowedMethod("PUT");
			config.addAllowedMethod("DELETE");
			config.addAllowedMethod("HEAD");
			source.registerCorsConfiguration("/**", config);
			return new CorsFilter(source);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable()
			.antMatcher(RestUrlConstants.CUST_URIS).authorizeRequests()
			.antMatchers(RestUrlConstants.CUST_LOGIN).permitAll()
			.antMatchers(RestUrlConstants.PUBLIC_CUST_URIS).permitAll()
			.antMatchers(RestUrlConstants.PRIVATE_CUST_URIS).access("hasRole('ROLE_CUSTOMER') OR hasRole('ROLE_ADMIN')")
			/*
			 * .antMatchers(HttpMethod.POST, RestUrlConstants.PUBLIC_URIS).permitAll()
			 * .antMatchers(HttpMethod.GET, RestUrlConstants.PUBLIC_URIS).permitAll()
			 * .antMatchers(HttpMethod.HEAD, RestUrlConstants.PUBLIC_URIS).permitAll()
			 */
			//.anyRequest().authenticated()
			.and()
			.addFilter(getJWTAuthenticationFilter())
			.addFilter(new JWTAuthorizationFilter(authenticationManager()));
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			web
			.ignoring()
			.antMatchers("/resources/**", "/static/**","/assets/**", "/css/**", "/js/**", "/images/**", "/bower_components/**", "/app/**","/rest/**");
		}


		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);
		}


		public JWTAuthenticationFilter getJWTAuthenticationFilter() throws Exception { 
			final JWTAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager()); 
			filter.setFilterProcessesUrl(RestUrlConstants.CUST_LOGIN); 
			return filter; 
		}

	}

	@Configuration
	public class SecurityConfiguration2 extends WebSecurityConfigurerAdapter { 
		@Bean("Admin")
		public CorsFilter corsFilter() {
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowCredentials(true);
			config.addAllowedOrigin("*");
			config.addAllowedHeader("*");
			config.addAllowedMethod("OPTIONS");
			config.addAllowedMethod("GET");
			config.addAllowedMethod("POST");
			config.addAllowedMethod("PUT");
			config.addAllowedMethod("DELETE");
			config.addAllowedMethod("HEAD");
			source.registerCorsConfiguration("/**", config);
			return new CorsFilter(source);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable()
			.antMatcher(RestUrlConstants.ADMIN_URIS).authorizeRequests()
			.antMatchers(RestUrlConstants.ADMIN_LOGIN).permitAll()
			.antMatchers(RestUrlConstants.PUBLIC_ADMIN_URIS).permitAll()
			.antMatchers(RestUrlConstants.PRIVATE_ADMIN_URIS).access("hasRole('ROLE_ADMIN')")
			/*
			 * .antMatchers(HttpMethod.POST, RestUrlConstants.PUBLIC_URIS).permitAll()
			 * .antMatchers(HttpMethod.GET, RestUrlConstants.PUBLIC_URIS).permitAll()
			 * .antMatchers(HttpMethod.HEAD, RestUrlConstants.PUBLIC_URIS).permitAll()
			 */
			// .antMatchers(HttpMethod.GET, "/assets/**").permitAll()
			//.anyRequest().authenticated()
			.and()
			.addFilter(getJWTAuthenticationFilter())
			.addFilter(new JWTAuthorizationFilter(authenticationManager()));
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			web
			.ignoring()
			.antMatchers("/resources/**", "/static/**","/assets/**", "/css/**", "/js/**", "/images/**", "/bower_components/**", "/app/**","/rest/**");
		}


		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(adminDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);
		}


		public JWTAuthenticationFilter getJWTAuthenticationFilter() throws Exception { 
			final JWTAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager()); 
			filter.setFilterProcessesUrl(RestUrlConstants.ADMIN_LOGIN); 
			return filter; 
		}
	}
}