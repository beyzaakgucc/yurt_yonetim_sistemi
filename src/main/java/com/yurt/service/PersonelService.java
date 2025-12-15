package com.yurt.service;

import com.yurt.domain.Personel;
import com.yurt.dto.PersonelKayitRequest;
import com.yurt.factory.PersonelFactory;
import com.yurt.repository.PersonelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonelService {

    private final PersonelRepository personelRepository;
    private final PersonelFactory personelFactory;

    public PersonelService(PersonelRepository personelRepository) {
        this.personelRepository = personelRepository;
        this.personelFactory = new PersonelFactory();
    }

    // CREATE
    public Personel personelEkle(PersonelKayitRequest dto) {
        Personel personel = personelFactory.create(dto);
        return personelRepository.save(personel);
    }

    // READ
    public List<Personel> tumPersoneller() {
        return personelRepository.findAll();
    }
}
