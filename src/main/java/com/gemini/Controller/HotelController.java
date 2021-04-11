package com.gemini.Controller;


import com.gemini.Entity.Address;
import com.gemini.Entity.Hotel;
import com.gemini.Entity.Review;
import com.gemini.Service.HotelService;
import com.mongodb.lang.NonNull;
import com.sun.org.glassfish.gmbal.Description;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;



@RestController
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping(value = "/hotel/all",produces = "application/json")
    public ResponseEntity<Collection<Hotel>> getAll()
    {
        return hotelService.getHotels();
    }

    @Description("Hellow World")
    @GetMapping(value="/hotel/{id}",produces = "application/json")
    public ResponseEntity<Optional<Hotel>> getHotelById( @PathVariable String id)
    {
        return hotelService.getHotelsById(id);
    }

    @Description("Hellow World")
    @PostMapping(value = "/hotel" ,consumes = "application/json",produces = "application/json")
    public ResponseEntity<Hotel> createHotel( @RequestBody Hotel hotel)
    {
        if(hotel.getAddress()==null || hotel.getName()==null || hotel.getId().isEmpty())
        {
            return new ResponseEntity<>(hotel,HttpStatus.BAD_REQUEST);
        }

        return hotelService.createHotel(hotel);
    }

    @DeleteMapping(value = "/hotel/delete/{id}",produces = "application/json")
    public ResponseEntity<Optional<Hotel>> deleteHotel(@PathVariable String id)
    {
        return hotelService.deleteHotel(id);
    }

    @PutMapping(value = "/hotel/update/review/{id}",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Optional<Hotel>> updateReview(@PathVariable String id, @RequestBody Review review)
    {
        if(review.getRating()<=0 || review.getRating()>10 || review.getUserName().isEmpty())
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        return hotelService.updateReview(id,review);
    }

    @PutMapping(value = "/hotel/update/address/{id}",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Optional<Hotel>> updateAddress(@PathVariable String id, @RequestBody Address address)
    {
        if (address.getCountry().isEmpty()||address.getCity().isEmpty())
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        return hotelService.updateAddress(id,address);
    }








}
