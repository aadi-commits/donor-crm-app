package com.donor.crm.dto.donor;

import com.donor.crm.model.DonorCategory;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonorDto {

    @NotBlank(message = "name is mandatory")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phone;

    @Size(max = 255, message = "255 characters limit exceed")
    private String address;

    @NotNull(message = "Donor category is required")
    private DonorCategory donorCategory;

}
