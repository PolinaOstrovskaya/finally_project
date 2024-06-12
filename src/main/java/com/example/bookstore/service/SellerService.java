package com.example.bookstore.service;

import com.example.bookstore.model.Seller;
import com.example.bookstore.model.dto.SellerCreateDTO;
import com.example.bookstore.repository.SellerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Optional<Seller> getSellerById(Long id) {
        return sellerRepository.findById(id);
    }

    public Boolean deleteSellerById(Long id) {
        sellerRepository.deleteById(id);
        return getSellerById(id).isEmpty();
    }

    public Boolean createSeller(@Valid SellerCreateDTO sellerFromDto) {
        Seller seller = new Seller();
        seller.setSurname(sellerFromDto.getSurname());
        seller.setName(sellerFromDto.getName());
        seller.setAddress(sellerFromDto.getAddress());
        seller.setNumberTelephone(sellerFromDto.getNumberTelephone());
        Seller createdSeller = sellerRepository.save(seller);
        return getSellerById(createdSeller.getId()).isPresent();
    }

    public Boolean updateSeller(Seller seller) {
        Optional<Seller> sellerFromDBOptional = sellerRepository.findById(seller.getId());
        Seller sellerFromDB = sellerFromDBOptional.get();
        sellerFromDB.setSurname(seller.getSurname());
        sellerFromDB.setName(seller.getName());
        sellerFromDB.setAddress(seller.getAddress());
        sellerFromDB.setNumberTelephone(seller.getNumberTelephone());
        Seller updatedSeller = sellerRepository.saveAndFlush(sellerFromDB);
        return sellerFromDB.equals(updatedSeller);

    }
}

