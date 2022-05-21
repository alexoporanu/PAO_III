package Client;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Address {
    private String country, county, city, street;

    public Address() {
    }
    public Address(String country, String county, String city, String street) {
        this.country = country;
        this.county = county;
        this.city = city;
        this.street = street;
    }

    public Address(String field, String field1, String field2, int parseInt) {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        String toReturn = "";
        toReturn += "Country: " + country + "\n";
        toReturn += "County: " + county + "\n";
        toReturn += "City: " + city + "\n";
        toReturn += "Street: " + street + "\n";
        return toReturn;
    }

    public void read(Scanner in){
        System.out.println("Introduce Address");
        System.out.println("Country: ");
        this.country = (in.nextLine());
        System.out.println("County: ");
        this.county = in.nextLine();
        System.out.println("City: ");
        this.city = in.nextLine();
        System.out.println("Street: ");
        this.street = in.nextLine();
    }

    public String toCSV() {
        return this.country + "," + this.county + "," + this.city + "," + this.street;
    }

}

