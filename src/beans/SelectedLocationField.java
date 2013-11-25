/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Phani Rahul
 */
public class SelectedLocationField {
    private String selectedState;
    private String selectedPlace;

    public SelectedLocationField(String selectedState, String selectedPlace) {
        this.selectedState = selectedState;
        this.selectedPlace = selectedPlace;
    }

    public String getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(String selectedState) {
        this.selectedState = selectedState;
    }

    public String getSelectedPlace() {
        return selectedPlace;
    }

    public void setSelectedPlace(String selectedPlace) {
        this.selectedPlace = selectedPlace;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.selectedState != null ? this.selectedState.hashCode() : 0);
        hash = 89 * hash + (this.selectedPlace != null ? this.selectedPlace.hashCode() : 0);
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
        final SelectedLocationField other = (SelectedLocationField) obj;
        if ((this.selectedState == null) ? (other.selectedState != null) : !this.selectedState.equals(other.selectedState)) {
            return false;
        }
        if ((this.selectedPlace == null) ? (other.selectedPlace != null) : !this.selectedPlace.equals(other.selectedPlace)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SelectedLocationField{" + "selectedState=" + selectedState + ", selectedPlace=" + selectedPlace + '}';
    }
    
    
}
