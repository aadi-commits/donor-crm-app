package com.donor.crm.service;

import com.donor.crm.dto.donor.DonorDto;
import com.donor.crm.dto.donor.DonorResponseDto;
import com.donor.crm.model.DonorCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DonorService {

    DonorResponseDto createDonor(DonorDto donor);
    DonorResponseDto getDonorById(Long id);
    List<DonorResponseDto> getAllDonors();
    List<DonorResponseDto> filterDonors(String name, String email, DonorCategory donorCategory);
    DonorResponseDto updateDonor(Long id,DonorDto donorDto);
    void deleteDonor(Long id);
    Page<DonorResponseDto> getAllDonors(Pageable pageable);
}
