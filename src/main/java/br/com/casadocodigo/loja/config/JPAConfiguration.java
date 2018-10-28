package br.com.casadocodigo.loja.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement //Habilita o Spring para realizar as transacoes
public class JPAConfiguration {
	
	@Bean //Para o spring visualizar esse metodo
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		//Cria a fabrica de EntityManager
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean(); 
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		//Seta a especificacao do JPA que será utilizado
		factory.setJpaVendorAdapter(vendorAdapter);
		
		//cria as configurações do datasource
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/springloja");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		factory.setDataSource(dataSource);
		
		//Adiciona as properties do hibernate
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		factory.setJpaProperties(properties);
		
		//Seta onde estao as entidades que serão persistidas
		factory.setPackagesToScan("br.com.casadocodigo.loja.model");
		
		return factory;
	}
	
	//Cria as transacoes do EntityManager
	@Bean  
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
		
	}
}
