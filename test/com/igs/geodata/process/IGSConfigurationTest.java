/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.igs.geodata.process;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zahid
 */
public class IGSConfigurationTest extends IGSTest{

    public IGSConfigurationTest() {
    }


    @Test
    public void testGetInstance() {

        IGSConfiguration instance = IGSConfiguration.configure();
        assertNotNull(instance);
        assertNotNull(instance.getProps());
        assertEquals("jdbc:mysql://localhost:3306/Test_Db", instance.getProps().getProperty("hibernate.connection.url"));
        assertEquals("com.mysql.jdbc.Driver", instance.getProps().getProperty("hibernate.connection.driver_class"));
        assertEquals("root", instance.getProps().getProperty("hibernate.connection.username"));
        assertEquals("MELjXm", instance.getProps().getProperty("hibernate.connection.password"));
        assertNotNull(instance.getSessionFactory());
        assertNotNull(instance.getProps());
        assertEquals("/home/zahid/Documents/java_task_test_file_path/geojson/test1.geojson", instance.getJsonFilePath());
    }

}
