package com.micro.hotel.controllers;

import com.micro.hotel.entities.Hotel;
import com.micro.hotel.exceptions.ApiResponse;
import com.micro.hotel.services.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@Tag(name = "Hotel Management", description = "APIs for managing hotels")
public class HotelController {

    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private HotelService hotelService;

    @PostMapping
    @Operation(summary = "Create a new hotel", description = "Create and save a new hotel to the database")
    public ResponseEntity<ApiResponse> createHotel(@RequestBody Hotel hotel) {
        logger.info("Creating hotel with name: {}", hotel.getName());
        Hotel createdHotel = hotelService.create(hotel);
        ApiResponse response = new ApiResponse("Hotel created successfully", true, createdHotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get hotel by ID", description = "Fetch a hotel by its unique ID")
    public ResponseEntity<ApiResponse> getHotel(@PathVariable String id) {
        logger.info("Fetching hotel with ID: {}", id);
        Hotel hotel = hotelService.get(id);
        ApiResponse response = new ApiResponse("Hotel retrieved successfully", true, hotel);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all hotels", description = "Retrieve all hotels from the database")
    public ResponseEntity<ApiResponse> getAll() {
        logger.info("Fetching all hotels");
        List<Hotel> hotels = hotelService.getAll();
        ApiResponse response = new ApiResponse("Hotels retrieved successfully", true, hotels);
        return ResponseEntity.ok(response);
    }
}


