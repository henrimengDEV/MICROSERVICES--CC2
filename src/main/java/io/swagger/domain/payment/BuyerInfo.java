package io.swagger.domain.payment;

public final class BuyerInfo {

    private final String firstname;
    private final String lastname;
    private final String phone;
    private final String address;
    private final String zipcode;

    public BuyerInfo(String firstname, String lastname, String phone, String address, String zipcode) {
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
