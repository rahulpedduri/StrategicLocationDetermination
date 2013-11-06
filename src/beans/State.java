/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Phani Rahul
 */
public class State {
    private String name;
    private String code;

    public State(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public State() {
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + (this.code != null ? this.code.hashCode() : 0);
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
        final State other = (State) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "State{" + "name=" + name + '}';
    }
    
}
