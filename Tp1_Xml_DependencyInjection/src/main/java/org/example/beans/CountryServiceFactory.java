package org.example.beans;

public class CountryServiceFactory {

    public Country getInstance(String name, Long population) {
        return new Country(name, population);
    }
    public Country getInstance() {
        return new Country();
    }
}
