package com.donor.crm.controller;

import com.donor.crm.dto.donor.DonorDto;
import com.donor.crm.dto.donor.DonorResponseDto;
import com.donor.crm.model.DonorCategory;
import com.donor.crm.payload.response.ApiResponse;
import com.donor.crm.service.DonorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/donors")
@RequiredArgsConstructor
@Slf4j
public class DonorController {

    @Autowired
    private final DonorService donorService;

    //CREATE
    @PostMapping
    public ResponseEntity<DonorResponseDto> createDonor(@Valid @RequestBody DonorDto donorDto){
        log.info("Received donor create request for: {}", donorDto.getEmail());

        DonorResponseDto response = donorService.createDonor(donorDto);
        return ResponseEntity.ok(response);
    }

    //READ ALL
    @GetMapping
    public ResponseEntity<Page<DonorResponseDto>> getAllDonors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "donorId") String sortBy
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(donorService.getAllDonors(pageable));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<DonorResponseDto>> filterDonors(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false)DonorCategory donorCategory
            ){
        return ResponseEntity.ok(donorService.filterDonors(name, email, donorCategory));
    }

    //READ ALL
//    @GetMapping
//    public ResponseEntity<List<DonorResponseDto>> getAllDonors(){
//        return ResponseEntity.ok(donorService.getAllDonors());
//    }

    //READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<DonorResponseDto> getDonorById(@PathVariable Long id){
        return ResponseEntity.ok(donorService.getDonorById(id));
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateDonor(@PathVariable Long id,
                                                        @Valid @RequestBody DonorDto donorDto, HttpServletRequest request){
        donorService.updateDonor(id, donorDto);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Donor update successfully!")
                .statusCode(HttpStatus.OK.value())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build());
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDonor(@PathVariable Long id){
        donorService.deleteDonor(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Donor deleted successfully!")
                .build());
    }
}
