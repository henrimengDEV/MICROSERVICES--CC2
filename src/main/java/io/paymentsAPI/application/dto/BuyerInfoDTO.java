package io.paymentsAPI.application.dto;

public class BuyerInfoDTO {

    String firstname;
    String lastname;
    String phone;
    String address;
    String zipcode;

    public BuyerInfoDTO(String firstname, String lastname, String phone, String address, String zipcode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.address = address;
        this.zipcode = zipcode;
    }
}
