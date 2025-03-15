package com.srz.rating.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.srz.rating.entities.Rating;

public interface RatingRepo extends MongoRepository<Rating, String> 
{
	//Custom Finder method
	
	List<Rating> findByUserId(String userId);
	
	List<Rating> findByHotelId(String hotelId);
	
}
