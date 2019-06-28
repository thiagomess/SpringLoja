package br.com.casadocodigo.loja.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


//Cria uma classe com as configurações do banco de dados para teste
public class DataSourceConfigurationTest {
	
	@Bean
	@Profile("test") // Seta o profile para informar no na classe de test o banco que iremos usar
	public DataSource dataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo_test");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		
		return dataSource;
	}
}
