package com.srz.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.srz.user.service.entities.Hotel;
import com.srz.user.service.entities.Rating;
import com.srz.user.service.entities.User;
import com.srz.user.service.exceptions.ResourceNotFoundException;
import com.srz.user.service.external.services.HotelService;
import com.srz.user.service.repositories.UserRepository;
import com.srz.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) 
	{
		String randomUserID = UUID.randomUUID().toString();
		user.setUserId(randomUserID);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : "+userId));
		
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		
		List<Rating> ratingList = ratings.stream().map(rating -> {
			//api call to hotel service to get the hotel
			
			//http://localhost:8082/hotels/f6d90470-5c74-414f-8dc9-c8d23c2a9fd0
			
//			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			
//			logger.info("response satus code : {}", forEntity.getStatusCode());
			
			rating.setHotel(hotel);
			
			return rating;
		}).collect(Collectors.toList());
		logger.info("{}",ratingsOfUser);
		
		user.setRatings(ratingList);
		return user;
	}

}
