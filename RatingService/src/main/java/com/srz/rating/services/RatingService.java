package com.srz.rating.services;

import java.util.List;

import com.srz.rating.entities.Rating;

public interface RatingService 
{
	Rating create(Rating rating);
	
	List<Rating> getratings();
	
	List<Rating> getRatingByUserId(String userId);
	
	List<Rating> getRatingByHotelId(String hotelId);
}
