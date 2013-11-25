/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Phani Rahul
 */
public class SelectedLocationMap {
    private String latitude;
    private String Longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.latitude != null ? this.latitude.hashCode() : 0);
        hash = 37 * hash + (this.Longitude != null ? this.Longitude.hashCode() : 0);
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
        final SelectedLocationMap other = (SelectedLocationMap) obj;
        if ((this.latitude == null) ? (other.latitude != null) : !this.latitude.equals(other.latitude)) {
            return false;
        }
        if ((this.Longitude == null) ? (other.Longitude != null) : !this.Longitude.equals(other.Longitude)) {
            return false;
        }
        return true;
    }
    
}
