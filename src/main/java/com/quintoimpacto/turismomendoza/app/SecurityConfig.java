package com.quintoimpacto.turismomendoza.app;

import com.quintoimpacto.turismomendoza.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("usuarioService")
    private UsuarioService usuarioService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()

				.antMatchers("/css/*", "/js/*", "/img/*", "/**","/main/**","/usuario/**","/actividad/**","/login/**", "/glosario/**").permitAll()  
				.and().formLogin()
					.loginPage("/login")
						.loginProcessingUrl("/logincheck")
						.usernameParameter("correo")
						.passwordParameter("contrasena")
						.defaultSuccessUrl("/inicio")
						.failureUrl("/login?error=error")
						.permitAll()
				.and().logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login")
					.permitAll()
				.and().csrf()
					.disable();
        }
	
}