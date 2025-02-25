package gr.aueb.mvcfilmbro.service;

import gr.aueb.mvcfilmbro.model.Country;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    private final List<Country> countries = new ArrayList<>();

    public CountryService() {
        countries.add(new Country("US", "United States"));
        countries.add(new Country("CA", "Canada"));
        countries.add(new Country("GB", "United Kingdom"));
        countries.add(new Country("DE", "Germany"));
        countries.add(new Country("FR", "France"));
        countries.add(new Country("IT", "Italy"));
        countries.add(new Country("ES", "Spain"));
        countries.add(new Country("IN", "India"));
        countries.add(new Country("AU", "Australia"));
        countries.add(new Country("JP", "Japan"));
        countries.add(new Country("KR", "South Korea"));
        countries.add(new Country("BR", "Brazil"));
        countries.add(new Country("MX", "Mexico"));
        countries.add(new Country("ZA", "South Africa"));
        countries.add(new Country("RU", "Russia"));
        countries.add(new Country("NG", "Nigeria"));
        countries.add(new Country("AR", "Argentina"));
        countries.add(new Country("CN", "China"));
        countries.add(new Country("SG", "Singapore"));
        countries.add(new Country("SE", "Sweden"));
        countries.add(new Country("GR", "Greece")); // ✅ Added Greece
        countries.add(new Country("NL", "Netherlands"));
        countries.add(new Country("BE", "Belgium"));
        countries.add(new Country("AT", "Austria"));
        countries.add(new Country("CH", "Switzerland"));
        countries.add(new Country("PT", "Portugal"));
        countries.add(new Country("DK", "Denmark"));
        countries.add(new Country("NO", "Norway"));
        countries.add(new Country("FI", "Finland"));
        countries.add(new Country("IE", "Ireland"));
        countries.add(new Country("PL", "Poland"));
        countries.add(new Country("HU", "Hungary"));
        countries.add(new Country("CZ", "Czech Republic"));
        countries.add(new Country("RO", "Romania"));
        countries.add(new Country("BG", "Bulgaria"));
        countries.add(new Country("TR", "Turkey"));
        countries.add(new Country("TH", "Thailand"));
        countries.add(new Country("MY", "Malaysia"));
        countries.add(new Country("ID", "Indonesia"));
        countries.add(new Country("PH", "Philippines"));
        countries.add(new Country("EG", "Egypt"));
        countries.add(new Country("IL", "Israel"));
        countries.add(new Country("SA", "Saudi Arabia"));
        countries.add(new Country("UA", "Ukraine"));
    }


    //Get all countries
    public List<Country> getAllCountries() {
        return countries;
    }

    //Get country name by ISO code
    public String getCountryNameByCode(String code) {
        return countries.stream()
                .filter(country -> country.getCode().equalsIgnoreCase(code))
                .map(Country::getName)
                .findFirst()
                .orElse("Choose a Country"); // Default text
    }
}
