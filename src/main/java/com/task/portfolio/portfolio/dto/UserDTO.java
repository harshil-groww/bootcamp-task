package com.task.portfolio.portfolio.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    private String id;

    @Size(min = 2, message = "name must be of at least two characters")
    private String name;

    @Size(min = 10, max = 10, message = "phone number must contain 10 digits")
    @Pattern(regexp = "\\d+", message = "phone number must contain 10 numberic characters")
    private String phoneNo;

    @Size(min = 10, max = 10, message = "PAN must contain 10 characters")
    private String pan;

    @Email
    private String emailId;
}
