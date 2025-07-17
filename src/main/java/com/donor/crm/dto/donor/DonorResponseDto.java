package com.donor.crm.dto.donor;

import com.donor.crm.model.DonorCategory;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonorResponseDto {

    private Long donorId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private DonorCategory donorCategory;
}
