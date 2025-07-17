package com.donor.crm.service.impl;

import com.donor.crm.dto.donor.DonorDto;
import com.donor.crm.dto.donor.DonorResponseDto;
import com.donor.crm.exception.EmailAlreadyExistsException;
import com.donor.crm.exception.ResourceNotFoundException;
import com.donor.crm.mapper.DonorMapper;
import com.donor.crm.model.Donor;
import com.donor.crm.model.DonorCategory;
import com.donor.crm.repository.DonorRepository;
import com.donor.crm.service.DonorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.donor.crm.specification.DonorSpecification.filter;
@Service
@Slf4j
@RequiredArgsConstructor
public class DonorServiceImpl implements DonorService {

    private final DonorRepository donorRepository;

//CREATE DONOR
    @Override
    public DonorResponseDto createDonor(DonorDto donorDto) {
        log.info("Creating donor with email: {}", donorDto.getEmail());

        if(donorRepository.existsByEmail(donorDto.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists: " + donorDto.getEmail());
        }

        Donor donor = DonorMapper.toEntity(donorDto);
        Donor savedDonor = donorRepository.save(donor);

        log.info("Donor saved: {}", savedDonor);

        return DonorMapper.toResponseDto(savedDonor);
    }

    //GET DONOR BY ID
    @Override
    public DonorResponseDto getDonorById(Long id) {

        Donor donor = donorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donor not found with id: " + id));
        return DonorMapper.toResponseDto(donor);
    }

    //GET ALL DONOR
    @Override
    public List<DonorResponseDto> getAllDonors() {
        return donorRepository.findAll()
                .stream()
                .map(DonorMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DonorResponseDto> filterDonors(String name, String email, DonorCategory donorCategory) {
        List<Donor> filteredDonor = donorRepository.findAll(filter(name, email, donorCategory));
        return filteredDonor.stream()
                .map(DonorMapper::toResponseDto)
                .toList();
    }

    //UPDATE DONOR
    public DonorResponseDto updateDonor(Long id, DonorDto donorDto){
        Donor donor = donorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donor not found with ID:" + id));

        donor.setName(donor.getName());
        donor.setEmail(donor.getEmail());
        donor.setPhone(donor.getPhone());
        donor.setAddress(donor.getAddress());
        donor.setDonorCategory(donor.getDonorCategory());

        Donor updateDonor = donorRepository.save(donor);
        return DonorMapper.toResponseDto(updateDonor);
    }

    //DELETE ALL DONOR
    @Override
    public void deleteDonor(Long id) {
        Donor donor = donorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donor not found with id: " + id));

        donorRepository.delete(donor);
    }

    @Override
    public Page<DonorResponseDto> getAllDonors(Pageable pageable) {
        return donorRepository.findAll(pageable)
                .map(DonorMapper::toResponseDto);
    }
}
