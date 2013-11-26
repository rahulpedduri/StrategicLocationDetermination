/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import util.CompetitorsFromPlacesAPI;

/**
 *
 * @author Phani Rahul
 */
public class Place implements Serializable, Comparable<Place>{

    private String name;
    private String code;
    private String latitude;
    private String longitude;
    private String population;
    private int cost;
    private int compSize;
    private double custGoodness;
    private double compGoodness;
    private double costGoodness;
    private List<Competitors> competitors = new ArrayList<Competitors>();
    private double zvalue;
    private String competitorJSON;
    private String state;

    public String getState() {
        return state;
    }

    public int getCompSize() {
        compSize = this.competitors.size();
        return compSize;
    }

    public void setCompSize(int compSize) {
        this.compSize = compSize;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getCustGoodness() {
        return custGoodness;
    }

    public void setCustGoodness(double custGoodness) {
        this.custGoodness = custGoodness;
    }

    public double getCompGoodness() {
        return compGoodness;
    }

    public void setCompGoodness(double compGoodness) {
        this.compGoodness = compGoodness;
    }
    

    public String getCompetitorJSON() {
        StringWriter out = new StringWriter();
//        try {
//            JSONValue.writeJSONString(getCompetitors(), out);
//        } catch (IOException ex) {
//            Logger.getLogger(Place.class.getName()).log(Level.SEVERE, null, ex);
//        }
        Iterator<Competitors> itr = getCompetitors().iterator();
            boolean comma = false;
            out.append("[");
            while(itr.hasNext()){
                
                Competitors c= itr.next();
                if(comma){
                    out.append(",");
                }
                comma=true;
                out.append("{");
                out.append("lat : ");
                out.append(c.getLatitude());
                out.append(",");
                out.append("long : ");
                out.append(c.getLongitude());
                out.append("}");
            }
            out.append("]");
        competitorJSON = out.toString();
        return competitorJSON;
    }

    public void setCompetitorJSON(String competitorJSON) {
        this.competitorJSON = competitorJSON;
    }
    

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
        StringWriter out = new StringWriter();
//        try {
            Iterator<Competitors> itr = getCompetitors().iterator();
            boolean comma = false;
            out.append("[");
            while(itr.hasNext()){
                
                Competitors c= itr.next();
                if(comma){
                    out.append(",");
                }
                comma=true;
                out.append("{");
                out.append("lat : ");
                out.append(c.getLatitude());
                out.append("long : ");
                out.append(c.getLongitude());
                out.append("}");
            }
            out.append("]");
            
            compSize = this.competitors.size();
            
          //  JSONValue.writeJSONString(getCompetitors(), out);
//        } catch (IOException ex) {
//            Logger.getLogger(Place.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //getting only lats and longs
        competitorJSON = out.toString();
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
        hash = 47 * hash + (this.name != null ? this.name.hashCode() : 0);
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
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Place{" + "name=" + name + ", code=" + code + ", latitude=" + latitude + ", longitude=" + longitude + ", population=" + population + '}';
    }

    @Override
    public int compareTo(Place o) {
        return (new Double(this.zvalue)).compareTo(o.zvalue);
    }
}