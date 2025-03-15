package com.srz.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srz.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>
{
	
}
