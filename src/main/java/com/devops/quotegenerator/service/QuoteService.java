package com.devops.quotegenerator.service;

import com.devops.quotegenerator.model.Quote;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class QuoteService {

    private final List<Quote> quotes = new ArrayList<>();
    private final AtomicInteger nextId = new AtomicInteger(1);
    private final Random random = new Random();

    public QuoteService() {
        addQuote("Believe you can and you're halfway there.", "Theodore Roosevelt");
        addQuote("The only way to do great work is to love what you do.", "Steve Jobs");
        addQuote("It always seems impossible until it's done.", "Nelson Mandela");
        addQuote("Dream big and dare to fail.", "Norman Vaughan");
        addQuote("Success is the sum of small efforts repeated daily.", "Robert Collier");
    }

    public Quote getRandomQuote() {
        int index = random.nextInt(quotes.size());
        return quotes.get(index);
    }

    public Optional<Quote> getQuoteById(int id) {
        return quotes.stream()
                .filter(quote -> quote.id() == id)
                .findFirst();
    }

    public Quote addQuote(String text, String author) {
        Quote quote = new Quote(nextId.getAndIncrement(), text, author);
        quotes.add(quote);
        return quote;
    }
}
