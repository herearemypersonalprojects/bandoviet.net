package net.bandoviet.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

  @Autowired
  CountryRepository countryRepository;

  public List<Country> getListCountry() {
    return countryRepository.findAll();
  }

  public List<Country> getCode(String name) {
    return countryRepository.findCode(name);
  }
}
