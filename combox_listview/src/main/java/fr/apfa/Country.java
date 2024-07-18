package fr.apfa;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Country {

    private String name;
    private String isoCode;

    public Country(String name, String isoCode) {
        this.name = name;
        this.isoCode = isoCode;
    }

    public String getName() {
        return this.name;
    }

    public String getIsoCode() {
        return this.isoCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    @Override
    public String toString() {
        return name + "|" + isoCode;

    }

    public static List<Country> getContriesList() {
        ArrayList<Country> countries = new ArrayList<>();
        String[] countriescode = Locale.getISOCountries();

        for (String countryCode : countriescode) {
            Locale obj = Locale.of("", countryCode);
            countries.add(new Country(obj.getDisplayCountry(), obj.getISO3Country()));
        }

        return countries;
    }

}
