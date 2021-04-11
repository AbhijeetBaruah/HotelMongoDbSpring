package com.gemini.Service;

import com.gemini.DAO.Dbseeder;
import com.gemini.Entity.Address;
import com.gemini.Entity.Hotel;
import com.gemini.Entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {


    @Autowired
    Dbseeder daoHotel;
    public ResponseEntity<Collection<Hotel>> getHotels() {
        return daoHotel.getHotels();
    }

    public ResponseEntity<Hotel> createHotel(Hotel hotel) {
        return daoHotel.createHotel(hotel);
    }

    public ResponseEntity<Optional<Hotel>> getHotelsById(String id) {
        return daoHotel.getHotelById(id);
    }

    public ResponseEntity<Optional<Hotel>> deleteHotel(String id) {
        return daoHotel.deleteHotel(id);
    }

    public ResponseEntity<Optional<Hotel>> updateReview(String id, Review review) {
        return daoHotel.updateReview(id,review);
    }

    public ResponseEntity<Optional<Hotel>> updateAddress(String id, Address address) {

        return daoHotel.updateAddress(id,address);
    }
}
