package com.insurance.service;

import com.insurance.entity.Card;
import com.insurance.entity.Transaction;
import com.insurance.repository.CardRepository;
import com.insurance.repository.TransactionRepository;
import com.insurance.request.TransactionReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CardRepository cardRepository) {
        this.transactionRepository = transactionRepository;
        this.cardRepository = cardRepository;
    }

    public Transaction findById(long id) {
        Transaction transaction = transactionRepository.findById(id).get();
        transaction.setCard(null);
        return transaction;
    }

    public List<Transaction> findAllByCardId(long cardId) {
        return transactionRepository.findAllByCard_Id(cardId);
    }


    public void save(TransactionReq request) {
        Transaction transaction = new Transaction();
        transaction.setName(request.getName());
        transaction.setSum(request.getSum());
        Card card = cardRepository.findById(request.getCardId()).get();
        transaction.setCard(card);
        transactionRepository.save(transaction);
    }

    public void deleteById(long id) {
        Transaction transaction=transactionRepository.findById(id).get();
        transaction.setCard(null);
        transactionRepository.delete(transaction);
    }


}
