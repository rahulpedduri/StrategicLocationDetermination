/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;

import util.CompetitorsFromPlacesAPI;

/**
 *
 * @author Phani Rahul
 */
public class Place {
    private String name;
    private String code;
    private String latitude;
    private String longitude;
    private String population;
    private int cost;
    private double custGoodness;
    private double compGoodness;
    private double costGoodness;
    private List<Competitors> competitors;
    private double zvalue;
    public double getZvalue() {
		return zvalue;
	}

	public void setZvalue(double zvalue) {
		this.zvalue = zvalue;
	}

	public Place(String name, String code, String latitude, String longitude, String population) {
        this.name = name;
        this.code = code;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
        this.custGoodness = 0; 
        this.compGoodness = 0;
        this.costGoodness = 0;
    }
    
    public Place() {
    }

    public double getCustomerGoodness() {
		return custGoodness;
	}

	public void setCustomerGoodness(double custGoodness) {
		this.custGoodness = custGoodness;
	}
	
	public double getCompetitorGoodness() {
		return compGoodness;
	}

	public void setCompetitorGoodness(double compGoodness) {
		this.compGoodness = compGoodness;
	}

	public double getCostGoodness() {
		return costGoodness;
	}

	public void setCostGoodness(double costGoodness) {
		this.costGoodness = costGoodness;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Competitors> getCompetitors() {
		return competitors;
	}

	public void setCompetitors(List<Competitors> competitors) {
		this.competitors = (List<Competitors>) new CompetitorsFromPlacesAPI().getCompetitors(latitude, longitude);
	}

	public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    
    public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.code != null ? this.code.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Place other = (Place) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Place{" + "name=" + name + ", code=" + code + ", latitude=" + latitude + ", longitude=" + longitude + ", population=" + population + '}';
    }   
}