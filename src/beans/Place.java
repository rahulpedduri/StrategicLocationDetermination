/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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

    public Place(String name, String code, String latitude, String longitude, String population) {
        this.name = name;
        this.code = code;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
    }

    public Place() {
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
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
