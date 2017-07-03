package com.igs.geodata.process.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zahid
 */
public class RockCategoryBuilder {

    private String categoryName;
    private String rcs;
    private String rcs_d;
    private String age_onegl;
    private List<Coordinate> coordinates = new ArrayList<>();

    public RockCategoryBuilder() {
    }

    public RockCategoryBuilder setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public RockCategoryBuilder setRcs(String rcs) {
        this.rcs = rcs;
        return this;
    }

    public RockCategoryBuilder setRcs_d(String rcs_d) {
        this.rcs_d = rcs_d;
        return this;
    }

    public RockCategoryBuilder setAge_onegl(String age_onegl) {
        this.age_onegl = age_onegl;
        return this;
    }

    public RockCategoryBuilder setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public RockCategory build() {
        return new RockCategory(categoryName, rcs, rcs_d, age_onegl, coordinates);
    }

}
