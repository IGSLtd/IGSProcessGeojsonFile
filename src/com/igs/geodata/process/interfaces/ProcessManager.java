package com.igs.geodata.process.interfaces;

/**
 *
 * @author zahid
 */


public interface ProcessManager {
	
	public boolean add(Object obj);
        
        //for mapped objects
        //obj1 is the main table, which create 1..* relationship
        //e.g. 1 RockCategory object can have multiple Coordinates
        public boolean add(Object obj1, Object obj2);
	
	public boolean remove(long objId);
	
	public boolean update(Object obj, long objId);

}
