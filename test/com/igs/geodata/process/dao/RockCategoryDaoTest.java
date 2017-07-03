/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.igs.geodata.process.dao;

import com.igs.geodata.process.IGSConfiguration;
import com.igs.geodata.process.IGSTest;
import com.igs.geodata.process.entity.Coordinate;
import com.igs.geodata.process.entity.RockCategory;
import com.igs.geodata.process.entity.RockCategoryBuilder;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
public class RockCategoryDaoTest extends IGSTest {

    @Test
    public void testAdd() {

        RockCategoryDao instance = new RockCategoryDao();
        assertNotNull(instance);
        assertFalse(instance.add(null));
        assertFalse(instance.add(new Object()));
        RockCategoryBuilder builder = new RockCategoryBuilder();
        assertFalse(instance.add(builder.build()));
        builder.setCategoryName("This is test2 category");
        assertFalse(instance.add(builder.build()));
        builder.setRcs("This is test2 RCS");
        assertFalse(instance.add(builder.build()));
        builder.setRcs_d("This is test2 RCS_D");
        assertFalse(instance.add(builder.build()));
        builder.setAge_onegl("This is test2 Age_onegl");
        assertTrue(instance.add(builder.build()));
        assertTrue(dirty("RockCategory", "categoryId"));
    }

    @Test
    public void testAddMappedCategory() throws Exception {

        RockCategoryDao instance = new RockCategoryDao();
        assertNotNull(instance);

        dirty(null);
        assertFalse(instance.add(null, null));
        assertFalse(instance.add(new Object(), null));
        assertFalse(instance.add(new Object(), new Object()));
        assertFalse(instance.add(new RockCategory(), new Object()));
        assertFalse(instance.add(new RockCategory(), new Coordinate()));
        RockCategoryBuilder builder = new RockCategoryBuilder();
        assertFalse(instance.add(builder, new Coordinate()));
        builder.setCategoryName("This is test2 category");
        assertFalse(instance.add(builder, new Coordinate()));
        builder.setRcs("This is test2 RCS");
        assertFalse(instance.add(builder, new Coordinate()));
        builder.setRcs_d("This is test2 RCS_D");
        assertFalse(instance.add(builder, new Coordinate()));
        builder.setAge_onegl("This is test2 Age_onegl");

        Coordinate c = new Coordinate();
        c = new Coordinate();
        c.setxAxis(23.11f);
        c.setyAxis(-0.11f);
        builder.getCoordinates().add(c);

        c = new Coordinate();
        c.setxAxis(23.22f);
        c.setyAxis(-0.22f);
        builder.getCoordinates().add(c);

        c = new Coordinate();
        c.setxAxis(23.33f);
        c.setyAxis(-0.33f);
        builder.getCoordinates().add(c);

        assertTrue(instance.add(builder.build(), builder.getCoordinates()));

        RockCategory rc = getRockCategory();
        assertNotNull(rc);
        assertTrue(rc.getCategoryId() > 0);
        assertEquals("This is test2 category", rc.getCategoryName());
        assertEquals("This is test2 RCS", rc.getRcs());
        assertEquals("This is test2 RCS_D", rc.getRcs_d());
        assertEquals("This is test2 Age_onegl", rc.getAge_onegl());

        List<Coordinate> cordes = getCoordinatesWithCategoryId(rc.getCategoryId());

        assertEquals(3, cordes.size());
        assertEquals(23.22f, cordes.get(0).getxAxis(), 3f);
        assertEquals(-0.11f, cordes.get(0).getyAxis(), 3f);

        assertEquals(23.22f, cordes.get(1).getxAxis(), 3f);
        assertEquals(-0.22f, cordes.get(1).getyAxis(), 3f);

        assertEquals(23.33f, cordes.get(2).getxAxis(), 3f);
        assertEquals(-0.33f, cordes.get(2).getyAxis(), 3f);

        dirty(rc);

    }

    private RockCategory getRockCategory() {

        RockCategory rc = null;
        try {
            Session session = IGSConfiguration.configure().getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            rc = session.createQuery("from RockCategory where categoryName= :categoryName", RockCategory.class)
                    .setParameter("categoryName", "This is test2 category").uniqueResult();

            tx.commit();
            logger.debug(rc);
            return rc;
        } catch (Exception e) {

        }

        return rc;

    }

    private void dirty(RockCategory rc) {

        if (rc == null) {
            rc = getRockCategory();
        }

        if (rc != null && rc.getCategoryId() > 0) {

            dirty("Coordinate", "categoryId", rc.getCategoryId());
            dirty("RockCategory", "categoryId", rc.getCategoryId());
        }

    }

    /*
	@Test
	public void testRemove() {
		
	}
	
	@Test
	public void testUpdate() {
		
	}
     */
}
