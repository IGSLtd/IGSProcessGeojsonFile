package com.igs.geodata.process.dao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.igs.geodata.process.IGSConfiguration;
import com.igs.geodata.process.entity.Coordinate;
import com.igs.geodata.process.entity.RockCategory;
import com.igs.geodata.process.interfaces.ProcessManager;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author zahid
 */
public class RockCategoryDao implements ProcessManager {
    
    private Logger logger = Logger.getLogger(RockCategoryDao.class);
    
    @Override
    public boolean add(Object obj1, Object obj2) {
        
        try {
            if (obj1 == null || !(obj1 instanceof RockCategory)) {
                logger.debug("RockCategory object is null, cannot add the item.");
                return false;
            }
            
            if (obj2 == null) {
                logger.debug("Coordinate object is null, cannot add the item.");
                return false;
            }
            
            RockCategory cat = (RockCategory) obj1;            
            List<Coordinate> coordinates = (ArrayList<Coordinate>) obj2;
            
            if (StringUtils.isBlank(cat.getCategoryName())
                    || StringUtils.isBlank(cat.getRcs())
                    || StringUtils.isBlank(cat.getRcs_d())
                    || StringUtils.isBlank(cat.getAge_onegl())) {
                logger.debug("One of the field is blank/empty.Failed to add RockCategory");
                return false;
            }
            
            SessionFactory sessionFactory = IGSConfiguration.configure().getSessionFactory();
            
            Session session = sessionFactory.openSession();
            
            Transaction tx = session.beginTransaction();
            
            session.save(cat);
            
            long catId = cat.getCategoryId();
            logger.debug(cat);
            logger.debug("CategoryId=" + catId);
            for (int i = 0; i < coordinates.size(); i++) {
                Coordinate c = coordinates.get(i);
                c.setCategoryId(catId);
                session.save(c);
                logger.debug(c);
            }
            
            tx.commit();
            
            return true;
        } catch (Exception e) {
            logger.error(e, e);
        }
        
        return false;
        
    }
    
    @Override
    public boolean add(Object obj) {
        
        if (obj != null && obj instanceof RockCategory) {
            
            RockCategory cat = (RockCategory) obj;
            
            if (StringUtils.isBlank(cat.getCategoryName())
                    || StringUtils.isBlank(cat.getRcs())
                    || StringUtils.isBlank(cat.getRcs_d())
                    || StringUtils.isBlank(cat.getAge_onegl())) {
                return false;
            }
            
            SessionFactory sessionFactory = IGSConfiguration.configure().getSessionFactory();
            
            Session session = sessionFactory.openSession();
            
            Transaction tx = session.beginTransaction();
            
            session.save(cat);
            
            tx.commit();
            
            return true;
            
        }
        
        return false;
    }
    
    @Override
    public boolean remove(long objId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean update(Object obj, long objId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
