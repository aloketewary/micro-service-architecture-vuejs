package com.moornmo.ltms;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
public class Application extends JpaBaseConfiguration {

	/**
	 * @param dataSource
	 * @param properties
	 * @param jtaTransactionManagerProvider
	 */
	protected Application(DataSource dataSource, JpaProperties properties,
						  ObjectProvider<JtaTransactionManager> jtaTransactionManagerProvider,
						  ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
		super(dataSource, properties, jtaTransactionManagerProvider, transactionManagerCustomizers);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration#createJpaVendorAdapter()
	 */
	@Override
	protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
		return new EclipseLinkJpaVendorAdapter();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration#getVendorProperties()
	 */
	@Override
	protected Map<String, Object> getVendorProperties() {

		// Turn off dynamic weaving to disable LTW lookup in static weaving mode
		return Collections.singletonMap("eclipselink.weaving", "false");
	}




	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

		ProgressRepository progressRepository = applicationContext.getBean(ProgressRepository.class);

		//test logics

//		Product product = new Product();
//		product.setpNo(1252l);
//
//		Progress progress = new Progress();
//		progress.setProduct(product);
//		progress.setProgName("progress");
//		progress.setProgType("type");
//
//		progressRepository.save(progress);

	}

}