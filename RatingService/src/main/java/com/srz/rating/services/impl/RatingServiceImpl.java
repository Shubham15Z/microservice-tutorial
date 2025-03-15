package com.srz.rating.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srz.rating.entities.Rating;
import com.srz.rating.repositories.RatingRepo;
import com.srz.rating.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService
{
	@Autowired
	private RatingRepo ratingRepository;
	
	
	@Override
	public Rating create(Rating rating) {
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getratings() {
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return ratingRepository.findByHotelId(hotelId);
	}

}
