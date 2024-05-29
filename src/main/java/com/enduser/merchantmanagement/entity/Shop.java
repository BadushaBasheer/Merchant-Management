package com.enduser.merchantmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopId;

    @NotBlank(message = "Owner name is required")
    @Size(max = 15, message = "Owner name must be less than or equal to 15 characters")
    private String ownerName;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?\\d+$", message = "Phone number must contain only digits and can start with '+'")
    private String phoneNumber;

    @NotBlank(message = "Shop name is required")
    @Size(max = 25, message = "Shop name must be less than or equal to 25 characters")
    private String shopName;

    @NotBlank(message = "Registration number is required")
    @Size(max = 10, message = "Registration number must be less than or equal to 10 characters")
    @Column(unique = true)
    private String registrationNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
}
