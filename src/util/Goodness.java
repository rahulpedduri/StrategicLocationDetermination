/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import beans.Competitors;
import beans.Place;

/**
 *
 * @author Phani Rahul
 */
public class Goodness {
	
	ArrayList<Place> locations=new ArrayList<Place>();
        int maxPop = 0;
	double goodness;
	double maxCost = 0.00;
	
	public Goodness(ArrayList<Place> locations) {
		
		this.locations = locations;
                maxPop=Integer.parseInt(locations.get(0).getPopulation());
                maxCost=(double)locations.get(0).getCost();
		maxPop();
		maxCost();
		this.goodness = 0;
	}
	
	private void maxCost() {
		// TODO Auto-generated method stub
		
		for (Place location : locations) {
			this.maxCost = Math.max(maxCost, (double)location.getCost());
		}
		
	}

	private void maxPop() {

		for (Place location : locations) {
			this.maxPop = Math.max(maxPop, Integer.parseInt(location.getPopulation()));
		}
		
	}
	
    public  void computeCustomerIndicator(){
    	
    	for(Place location : locations) {
    		double value = 0;
    		value = ((double)Integer.parseInt(location.getPopulation()))/maxPop;
    		location.setCustomerGoodness(value);
    	}
    	
    }
    
    public  void computeCompetitorIndicator(){
    	
    	for(Place location : locations) {
    		
    		double distance = 0;
    		
    		List<Competitors> competitors = location.getCompetitors();
    		Iterator<Competitors> compIter = competitors.iterator();
    		
    		while(compIter.hasNext()) {
    			double value = 0;
    			value = Math.pow(Double.parseDouble(location.getLatitude())-Double.parseDouble(compIter.next().getLatitude()),2);
    			value += Math.pow(Double.parseDouble(location.getLongitude())-Double.parseDouble(compIter.next().getLongitude()),2);
    			value = Math.sqrt(value);
    			distance += value;
    		}
    		
    		location.setCompetitorGoodness(distance);
    		
    	}
    	
    }
    
    public  ArrayList<Place> computeRealEstateIndicator(){
    	
    	for(Place location : locations) {
    		double value = 0;
    		value = location.getCost()/maxCost;
    		location.setCostGoodness(value);
    	}
    	setfinalValue();
        return locations;
        
    }

	private  void setfinalValue() {
		// TODO Auto-generated method stub
		for(Place location : locations) {
    	 location.setZvalue(location.getCostGoodness()+location.getCompetitorGoodness()+location.getCustomerGoodness());
    	}	
	}
    
    
    
}