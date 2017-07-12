/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.igs.geodata.process.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author zahid
 */

@Entity
@Table(name="Coordinate")
public class Coordinate implements Serializable {
    

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long coordinateId;
    
    private long categoryId;   
    private double xAxis, yAxis, lat, lon;
    @ManyToOne
    private RockCategory rockCategory;

    public Coordinate() {
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }


    public RockCategory getRockCategory() {
        return rockCategory;
    }

    public void setRockCategory(RockCategory rockCategory) {
        this.rockCategory = rockCategory;
    }

    public long getCoordinateId() {
        return coordinateId;
    }

    public void setCoordinateId(long coordinateId) {
        this.coordinateId = coordinateId;
    }

    public double getxAxis() {
        return xAxis;
    }

    public void setxAxis(double xAxis) {
        this.xAxis = xAxis;
    }

    public double getyAxis() {
        return yAxis;
    }

    public void setyAxis(double yAxis) {
        this.yAxis = yAxis;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Coordinate{" + "coordinateId=" + coordinateId + ", categoryId=" + categoryId + ", xAxis=" + xAxis + ", yAxis=" + yAxis + ", lat=" + lat + ", lon=" + lon + ", rockCategory=" + rockCategory + '}';
    }

}
