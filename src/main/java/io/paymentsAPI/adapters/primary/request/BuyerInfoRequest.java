package io.paymentsAPI.adapters.primary.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * BuyerInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-06-01T16:22:31.174Z")


public class BuyerInfoRequest {
    @JsonProperty("firstName")
    private String firstName = null;

    @JsonProperty("lastName")
    private String lastName = null;

    @JsonProperty("adress")
    private String adress = null;

    @JsonProperty("zipcode")
    private String zipcode = null;

    @JsonProperty("phone")
    private String phone = null;

    public BuyerInfoRequest firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Get firstName
     *
     * @return firstName
     **/
    @ApiModelProperty(value = "")


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public BuyerInfoRequest lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Get lastName
     *
     * @return lastName
     **/
    @ApiModelProperty(value = "")


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BuyerInfoRequest adress(String adress) {
        this.adress = adress;
        return this;
    }

    /**
     * Get adress
     *
     * @return adress
     **/
    @ApiModelProperty(value = "")


    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public BuyerInfoRequest zipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    /**
     * Get zipcode
     *
     * @return zipcode
     **/
    @ApiModelProperty(value = "")


    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public BuyerInfoRequest phone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Get phone
     *
     * @return phone
     **/
    @ApiModelProperty(value = "")


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BuyerInfoRequest buyerInfoRequest = (BuyerInfoRequest) o;
        return Objects.equals(this.firstName, buyerInfoRequest.firstName) &&
                Objects.equals(this.lastName, buyerInfoRequest.lastName) &&
                Objects.equals(this.adress, buyerInfoRequest.adress) &&
                Objects.equals(this.zipcode, buyerInfoRequest.zipcode) &&
                Objects.equals(this.phone, buyerInfoRequest.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, adress, zipcode, phone);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BuyerInfo {\n");

        sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
        sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
        sb.append("    adress: ").append(toIndentedString(adress)).append("\n");
        sb.append("    zipcode: ").append(toIndentedString(zipcode)).append("\n");
        sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

