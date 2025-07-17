package com.donor.crm.mapper;

import com.donor.crm.dto.donor.DonorDto;
import com.donor.crm.dto.donor.DonorResponseDto;
import com.donor.crm.model.Donor;
import com.donor.crm.model.DonorCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DonorMapperTest {

    @Test
    void testToEntity(){
        DonorDto dto = DonorDto.builder()
                .name("Adit Navle")
                .email("adit@gmail.com")
                .phone("9999999999")
                .address("Mumbai")
                .donorCategory(DonorCategory.INDIVIDUAL)
                .build();

        Donor donor = DonorMapper.toEntity(dto);

        assertEquals(dto.getName(), donor.getName());
        assertEquals(dto.getEmail(), donor.getEmail());
        assertEquals(dto.getPhone(), donor.getPhone());
        assertEquals(dto.getAddress(), donor.getAddress());
        assertEquals(dto.getDonorCategory(), donor.getDonorCategory());
    }

    @Test
    void testToResponseDto(){
        Donor donor = Donor.builder()
                .donorId(1L)
                .name("Alshad Navle")
                .email("akshadnavle@gmail.com")
                .phone("8888888888")
                .address("Mumbai")
                .donorCategory(DonorCategory.CORPORATE)
                .build();

        DonorResponseDto responseDto = DonorMapper.toResponseDto(donor);

        assertEquals(donor.getDonorId(), responseDto.getDonorId());
        assertEquals(donor.getName(), responseDto.getName());
        assertEquals(donor.getEmail(), responseDto.getEmail());
        assertEquals(donor.getPhone(), responseDto.getPhone());
        assertEquals(donor.getAddress(), responseDto.getAddress());
        assertEquals(donor.getDonorCategory(), responseDto.getDonorCategory());
    }
}
