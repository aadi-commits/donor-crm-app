package com.donor.crm.mapper;

import com.donor.crm.dto.donor.DonorDto;
import com.donor.crm.dto.donor.DonorResponseDto;
import com.donor.crm.model.Donor;

public class DonorMapper {

    public static Donor toEntity(DonorDto dto){
        return Donor.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .donorCategory(dto.getDonorCategory())
                .build();
    }

    public static DonorResponseDto toResponseDto(Donor donor){
        return DonorResponseDto.builder()
                .donorId(donor.getDonorId())
                .name(donor.getName())
                .email(donor.getEmail())
                .phone(donor.getPhone())
                .address(donor.getAddress())
                .donorCategory(donor.getDonorCategory())
                .build();
    }
}
