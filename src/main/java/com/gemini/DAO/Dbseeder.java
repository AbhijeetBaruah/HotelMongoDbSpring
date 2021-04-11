package com.gemini.DAO;


import com.gemini.Entity.Address;
import com.gemini.Entity.Hotel;
import com.gemini.Entity.Review;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Dbseeder{

    @Autowired
    private HotelRepository hotelRepository;

    public ResponseEntity<Collection<Hotel>> getHotels() {
        return new ResponseEntity<>(hotelRepository.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<Hotel> createHotel(Hotel hotel) {
        Optional<Hotel> hotel1 = hotelRepository.findById(hotel.getId());
        if(hotel1.isPresent())
        {
            return new ResponseEntity<>(hotel, HttpStatus.CONFLICT);
        }
        else
        {
            hotelRepository.insert(hotel);
            return new ResponseEntity<>(hotel,HttpStatus.OK);
        }
    }

    public ResponseEntity<Optional<Hotel>> getHotelById(String id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if(hotel.isPresent())
        {
            return new ResponseEntity<>(hotel,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(hotel,HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Optional<Hotel>> deleteHotel(String id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if(hotel.isPresent())
        {
            hotelRepository.deleteById(id);
            return new ResponseEntity<>(hotel,HttpStatus.OK);
        }
        return new ResponseEntity<>(hotel,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Optional<Hotel>> updateReview(String id, Review review) {

        Optional<Hotel> hotel = hotelRepository.findById(id);
        if(hotel.isPresent())
        {
            List<Review> reviews = hotel.get().getReview();
            reviews.add(review);
            hotel.ifPresent(hotel1 ->hotel1.setReview(reviews));
            hotel.ifPresent(hotel1 -> hotelRepository.save(hotel1));
            return new ResponseEntity<>(hotel,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(hotel,HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Optional<Hotel>> updateAddress(String id, Address address) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if(hotel.isPresent())
        {

            Address address1 = hotel.get().getAddress();
            address1.setCity(address.getCity());
            address1.setCountry(address.getCountry());
            hotel.ifPresent(hotel1 -> hotel1.setAddress(address1));
            hotel.ifPresent(hotel1 -> hotelRepository.save(hotel1));
            return new ResponseEntity<>(hotel,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(hotel,HttpStatus.NOT_FOUND);
        }
    }
}
