package com.devops.quotegenerator.controller;

import com.devops.quotegenerator.model.Quote;
import com.devops.quotegenerator.service.QuoteService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/quote")
public class QuoteController {

    private final QuoteService quoteService;

    @Value("${HOSTNAME:local}")
    private String podName;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public ResponseEntity<Quote> getRandomQuote() {
        return ResponseEntity.ok()
                .header("X-Served-By", podName)
                .body(quoteService.getRandomQuote());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuoteById(@PathVariable int id) {

        Optional<Quote> quote = quoteService.getQuoteById(id);

        if (quote.isPresent()) {
            return ResponseEntity.ok()
                    .header("X-Served-By", podName)
                    .body(quote.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header("X-Served-By", podName)
                .body(Map.of("error", "Quote not found with ID: " + id));
    }

    @PostMapping
    public ResponseEntity<Quote> addQuote(
            @RequestBody Map<String, String> request) {

        String text = request.get("text");
        String author = request.get("author");

        if (text == null || text.isBlank()
                || author == null || author.isBlank()) {
            return ResponseEntity.badRequest()
                    .header("X-Served-By", podName)
                    .build();
        }

        Quote quote = quoteService.addQuote(text, author);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("X-Served-By", podName)
                .body(quote);
    }
}
