package com.insurance.service;

import com.insurance.entity.Card;
import com.insurance.entity.Customer;
import com.insurance.repository.CardRepository;
import com.insurance.repository.CustomerRepository;
import com.insurance.request.CardReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    private final CardRepository repository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CardService(CardRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }


    public List<Card> findAllByCustomerId(long customerId) {

        return repository.findAllByCustomer_Id(customerId);
    }

    public void save(CardReq request) {
        Card card = new Card();
        card.setName(request.getName());
        card.setCardNumber(request.getCardNumber());
        Customer customer = customerRepository.findById(request.getCustomerId()).get();
        card.setCustomer(customer);
        repository.save(card);
    }

    public void deleteById(long id) {
        Card card=repository.findById(id).get();
        card.setCustomer(null);
        repository.delete(card);
    }

}
