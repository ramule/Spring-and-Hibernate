package com.niit.dbconfig;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.niit.Model.*;

public class DbConfig {
	private static SessionFactory factory;
	
	static {
		loadSessionFactory();
	}
	
	public static void loadSessionFactory() {
		Configuration cfg = new Configuration();
		cfg.configure("Hibernate.cfg.xml");
		cfg.addAnnotatedClass(User.class);
		ServiceRegistry registry = 	new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		factory= cfg.buildSessionFactory();
	}
	
	public static Session getSession() {
		return factory.openSession();
	}
}