package br.com.casadocodigo.loja.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


//Ativa a configurações de segurança, deve adicionar tb a classe a classe ServletSpringMVC
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService UsuarioDao;


	//A anotação é bem clara. Ela simplesmente habilita o recurso de Web MVC Secutiry e quem configura o recurso é justamente a classe WebSecurityConfigurerAdapter. Então, para solucionar o erro, precisaremos fazer com que a classe SecurityConfiguration herde da classe WebSecurityConfigurerAdapter 

	//Configura as roles e acesso aos usuarios
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
		    .antMatchers("/resources/**").permitAll()
		    .antMatchers("/carrinho/**").permitAll()
		    .antMatchers("/pagamento/**").permitAll()
		    .antMatchers("/produtos/form").hasRole("ADMIN")
		    .antMatchers(HttpMethod.GET, "/produtos").hasRole("ADMIN")
		    .antMatchers(HttpMethod.GET, "/produtos/").hasRole("ADMIN")
		    .antMatchers(HttpMethod.POST, "/produtos/").hasRole("ADMIN")
		    .antMatchers("/produtos/**").permitAll()
		    .antMatchers("/").permitAll()
		    .anyRequest().authenticated()
		    .and().formLogin().loginPage("/login").permitAll()
		    .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
	}
	
	//configuração de codificação das senhas dos usuários. Usaremos uma criptografia chamada BCrypt e faremos isto utilizando a classe BCryptPasswordEncoder. 
	//Em seguida, vamos usar o método passwordEncoder do objeto auth, no método configure da classe SecurityConfiguration.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(UsuarioDao)
		.passwordEncoder(new BCryptPasswordEncoder());
	}

}
