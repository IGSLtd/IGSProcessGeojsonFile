package com.igs.geodata.process;

import java.io.FileInputStream;
import java.util.Properties;
import org.hibernate.cfg.Configuration;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

/**
 *
 * @author zahid
 */


/*
 * 	Singleton object handle hibernate session factory and other properties file 
 */

public class IGSConfiguration {

	private static final Logger logger = Logger.getLogger(IGSConfiguration.class);
	private static SessionFactory sessionFactory;
	private static IGSConfiguration instance;
	private String jsonFilePath;
	private Properties props;

	private IGSConfiguration() {
	}

	public static IGSConfiguration configure() {

		if (instance == null)
			instance = new IGSConfiguration();

		instance.init();

		return instance;
	}

	private SessionFactory buildSessionFactory() {

		if (sessionFactory == null) {
			
			try {

				sessionFactory = new Configuration().mergeProperties(getProps()).configure().buildSessionFactory();

				return sessionFactory;

			} catch (Throwable ex) {
				logger.error(ex, ex);

			}
		}

		return sessionFactory;
	}

	private void init() {

		if (sessionFactory == null)
			sessionFactory = buildSessionFactory();

		this.jsonFilePath = getProps().getProperty("json.file.path");

	}

	protected Properties getProps() {

		if (props == null) {

			this.props = new Properties();
			try {
				/*
				props.load(IGSConfiguration.class.getClassLoader()
						.getSystemClassLoader()
						.getResourceAsStream("configuration.properties"));
						*/
				
				props.load(new FileInputStream("configuration.properties"));
				
			} catch (Exception e) {
				logger.error(e, e);
			}
		}

		return props;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public String getJsonFilePath() {
		return jsonFilePath;
	}

}
