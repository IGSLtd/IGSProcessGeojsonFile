package com.igs.geodata.process.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author zahid
 */
@Entity
public class RockCategory implements Serializable {

    //@Transient
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long categoryId;
    //@Column(name="catName")
    private String categoryName;
    private String rcs;
    private String rcs_d;
    private String age_onegl;
    @OneToMany(mappedBy = "rockCategory")
    private List<Coordinate> coordinates = new ArrayList<>();

    public RockCategory() {
    }

    public RockCategory(String categoryName, String rcs, String rcs_d, String age_onegl, List<Coordinate> coordinates) {
        this.categoryName = categoryName;
        this.rcs = rcs;
        this.rcs_d = rcs_d;
        this.age_onegl = age_onegl;
        this.coordinates = coordinates;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getRcs() {
        return rcs;
    }

    public void setRcs(String rcs) {
        this.rcs = rcs;
    }

    public String getRcs_d() {
        return rcs_d;
    }

    public void setRcs_d(String rcs_d) {
        this.rcs_d = rcs_d;
    }

    public String getAge_onegl() {
        return age_onegl;
    }

    public void setAge_onegl(String age_onegl) {
        this.age_onegl = age_onegl;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "RockCategory{" + "categoryId=" + categoryId + ", categoryName=" + categoryName + ", rcs=" + rcs + ", rcs_d=" + rcs_d + ", age_onegl=" + age_onegl + ", coordinates=" + coordinates + '}';
    }
    
    

}
