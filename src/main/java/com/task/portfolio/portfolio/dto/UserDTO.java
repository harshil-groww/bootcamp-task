package com.task.portfolio.portfolio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {

    private String Id;
    @Size (min = 2, message = "name must be of at least two characters")
    private String Name;
    @Pattern(regexp = "\\d{10}", message = "phone number must contain 10 numberic characters")
    private String PhoneNo;
    @Pattern(regexp = "[a-zA-Z0-9]{10}", message = "PAN must be an alphanumeric string of 10 characters")
    private String PAN;
    @Email
    private String EmailId;
}
