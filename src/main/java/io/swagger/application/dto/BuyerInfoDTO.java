package io.swagger.application.dto;

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

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getZipcode() {
        return zipcode;
    }
}
