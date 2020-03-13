package com.techelevator.npgeek;

import java.util.List;



public interface ParkDao {
	public void createPark(Park newPark);
	public List<Park> getAllParks();
	public Park getParkByCode(String parkCode);

}
