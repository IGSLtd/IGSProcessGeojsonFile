/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.igs.geodata.process;

import com.igs.geodata.process.entity.Coordinate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author zahid
 */
public class IGSTest {

    protected Logger logger = Logger.getLogger(IGSTest.class);

    private final String CREATE_ROCK_CATEGORY_TABLE = "CREATE TABLE `RockCategory` ("
            + " `categoryId` int(11) NOT NULL AUTO_INCREMENT,"
            + " `categoryName` varchar(100) DEFAULT NULL,"
            + " `rcs` varchar(45) DEFAULT NULL,"
            + " `rcs_d` varchar(100) DEFAULT NULL,"
            + " `age_onegl` varchar(45) DEFAULT NULL,"
            + " PRIMARY KEY (`categoryId`),"
            + " UNIQUE KEY `categoryId_UNIQUE` (`categoryId`),"
            + " UNIQUE KEY `categoryName_UNIQUE` (`categoryName`)"
            + " ) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=latin1;";

    private String createCoordinateTable = "CREATE TABLE `Coordinate` (\n"
            + "  `coordinateId` int(11) NOT NULL AUTO_INCREMENT,\n"
            + "  `categoryId` varchar(45) DEFAULT NULL,\n"
            + "  `lng` decimal(11,8) DEFAULT NULL,\n"
            + "  `lat` decimal(10,8) DEFAULT NULL,\n"
            + "  PRIMARY KEY (`coordinateId`),\n"
            + "  UNIQUE KEY `geoId_UNIQUE` (`coordinateId`)\n"
            + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        config();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testDropTable() {

        assertFalse(dirty("", null, ""));
        assertFalse(dirty(null, null, ""));
        assertFalse(dirty(null, null, null));
        assertFalse(dirty("RockCategory", null, null));
        assertFalse(dirty("RockCategory", null, "This is test _id"));
        //assertTrue(dirty("RockCategory", RockCategory.class, "categoryId"));
        logger.debug(CREATE_ROCK_CATEGORY_TABLE);
    }

    private void config() {

    }

    protected boolean dirty(final String tableName, String uniqueColumnName) {

        if (StringUtils.isNotBlank(tableName)
                && StringUtils.isNotBlank(uniqueColumnName)) {

            Session session = IGSConfiguration.configure().getSessionFactory().openSession();

            Transaction tx = session.beginTransaction();

            session.createNativeQuery("Delete from " + tableName + " where " + uniqueColumnName + ">0;").executeUpdate();

            tx.commit();
            return true;
        }

        return false;

    }

    protected boolean dirty(final String tableName, String uniqueColumnName, Object columnValue) 
    {

        if (StringUtils.isNotBlank(tableName)
                && StringUtils.isNotBlank(uniqueColumnName)
                && columnValue != null) {
            
            String columnTypeStm = columnValue + ";";
            
            if(columnValue instanceof String) {
                columnTypeStm = "'"+ columnValue + "'";
            }
            
            String stm = "Delete from " + tableName + " where " + uniqueColumnName + "=" + columnTypeStm;
            Session session = IGSConfiguration.configure().getSessionFactory().openSession();

            Transaction tx = session.beginTransaction();

            session.createNativeQuery(stm).executeUpdate();

            tx.commit();
            return true;
        }

        return false;

    }
    
    protected List<Coordinate> getCoordinatesWithCategoryId(long catId) throws Exception {

        if (catId == 0) {
            fail("categoryId is 0");
        }

        List<Coordinate> coordinates = new ArrayList<>();

        Session session = IGSConfiguration.configure().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        coordinates = session.createQuery("from Coordinate where categoryId= :categoryId", Coordinate.class)
                .setParameter("categoryId", catId).list();

        tx.commit();
        logger.debug(coordinates);

        return coordinates;

    }
    

}
