package com.igs.geodata.process;

import com.esri.core.geometry.Point;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.igs.geodata.process.businesslogic.IGSConversionHelper;
import com.igs.geodata.process.dao.RockCategoryDao;
import com.igs.geodata.process.entity.Coordinate;
import com.igs.geodata.process.entity.RockCategoryBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zahid
 */

/*
 * 
 * 	Use this object to extract geo json and add record on the database
 */
public class IGSGeojsonExtractor extends IGSExtractor {

    private final Gson gson = new Gson();
    private IGSConversionHelper conversionHelper = new IGSConversionHelper();

    @Override
    public void extractGeojson() {

        File f = new File(IGSConfiguration.configure().getJsonFilePath());

        if (!f.exists()) {
            logger.debug("geojson file is missing. please add a valid geson file on the configuration.properties file properties key='json.file.path'.");
        }

        logger.debug("File found, path=" + f.getAbsolutePath());

        JsonParser parser = new JsonParser();

        try {

            JsonObject json = (JsonObject) parser.parse(new FileReader(f.getAbsolutePath()));

            if (json.size() > 0 && json.has("features") && parseFeatures(json.getAsJsonArray("features"))) {
                logger.debug("BYE");
                System.exit(0);
            }

        } catch (FileNotFoundException e) {
            logger.error(e, e);
        } catch (IOException e) {
            logger.error(e, e);
        } catch (Exception ex) {
            logger.error(ex, ex);
        }

    }

    protected boolean parseFeatures(JsonArray features) throws Exception {

        boolean valid = false;

        if (features != null && features.size() > 0) {

            logger.debug("We have list of features.");

            RockCategoryDao dao = new RockCategoryDao();

            int size = features.size();

            logger.debug("Total elements found= " + size);

            for (int i = 0; i < size; i++) {
                JsonObject obj = features.get(i).getAsJsonObject();
                logger.debug("JSON to parse=" + obj);
                addRockCategory(obj, dao);
            }

            logger.debug("TOTAL ROWS INSERTED= " + size);
            valid = true;

        }

        return valid;
    }

    @Override
    public void extractShapefile() {
        throw new UnsupportedOperationException("Not supported yet.");

    }

    protected boolean addRockCategory(JsonObject json, RockCategoryDao dao) throws Exception {

        boolean valid = false;

        if (json.size() == 0 || !json.has("properties") || !json.has("geometry")) {
            logger.debug("Invalid json, 'properties' or 'geometry' not found. Failed to add record.");
            return valid;
        }

        if (dao == null) {
            logger.debug("DAO object is null, failed to add record.");
            return valid;
        }

        JsonObject properties = json.getAsJsonObject("properties");
        JsonObject geometry = json.getAsJsonObject("geometry");

        if (!geometry.has("coordinates")) {
            logger.debug("Invalid json, 'coordinates' not found. Failed to add record.");
            return valid;
        }

        JsonArray coordinates = geometry.getAsJsonArray("coordinates");

        List<Coordinate> coordinateLists = parseCoordinates(coordinates);

        if (coordinateLists.isEmpty()) {
            logger.debug("Could not parse list of 'coordinates'.");
            return valid;
        }

        if (properties != null && properties.size() > 0) {

            RockCategoryBuilder builder = new RockCategoryBuilder()
                    .setCategoryName(properties.get("LEX_D").getAsString())
                    .setRcs(properties.get("RCS").getAsString())
                    .setRcs_d(properties.get("RCS_D").getAsString())
                    .setAge_onegl(properties.get("AGE_ONEGL").getAsString());
            valid = dao.add(builder.build(), coordinateLists);

            logger.debug((valid) ? "Record added successfully." : "Failed to add record.");

        }

        return valid;

    }

    protected List<Coordinate> parseCoordinates(JsonArray coords) throws Exception {

        List<Coordinate> coordinates = new ArrayList<>();

        if (coords == null || coords.size() == 0) {
            logger.debug("Coordinates array is empty.");
            return coordinates;
        }

        List list = gson.fromJson((JsonArray) coords.get(0), ArrayList.class);

        if (list.isEmpty()) {
            logger.debug("Coordinates array is empty.");
            return coordinates;
        }

        for (int i = 0; i < list.size(); i++) {

            List l = (ArrayList) list.get(i);

            Coordinate c = new Coordinate();
            
            double x = (Double) l.get(0);
            double y = (Double) l.get(1);
            Point p = conversionHelper.fromBNGLngLat(new Point(x, y));
            c.setxAxis(x);
            c.setyAxis(y);
            c.setLat(p.getX());
            c.setLon(p.getY());
            
            logger.debug("BNG x=" + x + ", y=" + y + " after conversion lat=" + p.getX() + " long=" + p.getY());
            coordinates.add(c);
        }

        return coordinates;
    }
    


}
