package com.igs.geodata.process;

import org.apache.log4j.Logger;

import com.igs.geodata.process.interfaces.Extractor;


/**
 *
 * @author zahid
 */

public abstract class IGSExtractor implements Extractor {
	protected Logger logger = Logger.getLogger(IGSGeojsonExtractor.class);
	
	public IGSExtractor() {
		IGSConfiguration.configure();
	}

}
