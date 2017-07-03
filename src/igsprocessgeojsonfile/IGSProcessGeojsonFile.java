/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package igsprocessgeojsonfile;

import com.igs.geodata.process.IGSGeojsonExtractor;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author zahid
 */
public class IGSProcessGeojsonFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	IGSGeojsonExtractor extractor = new IGSGeojsonExtractor();
    	extractor.extractGeojson();
    }
    
}
