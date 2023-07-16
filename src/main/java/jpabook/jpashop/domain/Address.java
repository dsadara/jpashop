package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String city;
    private String zipcode;
    private String street;

    public Address() {

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.zipcode = zipcode;
        this.street = street;
    }
}
