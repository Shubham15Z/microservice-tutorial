package com.srz.hotel.entities.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srz.hotel.entities.Hotel;
import com.srz.hotel.exception.ResourceNotFoundException;
import com.srz.hotel.repositories.HotelRepository;
import com.srz.hotel.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService
{	
	@Autowired
	private HotelRepository hotelResository;
	
	@Override
	public Hotel create(Hotel hotel) {
		String hotelId = UUID.randomUUID().toString();
		hotel.setId(hotelId);
		return hotelResository.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {
		
		return hotelResository.findAll();
	}

	@Override
	public Hotel get(String id) {
		
		return hotelResository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel with given id not found !!"));
	}

}
