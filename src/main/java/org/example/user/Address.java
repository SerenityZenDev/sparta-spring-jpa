package org.example.user;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String city;
    private String status;
    private String zipCode;
}
