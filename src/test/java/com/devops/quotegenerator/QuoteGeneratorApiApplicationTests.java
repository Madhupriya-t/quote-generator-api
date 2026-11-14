package com.devops.quotegenerator;

import com.devops.quotegenerator.model.Quote;
import com.devops.quotegenerator.service.QuoteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuoteGeneratorApiApplicationTests {

    private QuoteService quoteService;

    @BeforeEach
    void setUp() {
        quoteService = new QuoteService();
    }

    @Test
    void shouldReturnRandomQuote() {

        Quote quote = quoteService.getRandomQuote();

        assertNotNull(quote);
        assertNotNull(quote.text());
        assertNotNull(quote.author());
    }

    @Test
    void shouldReturnQuoteById() {

        Quote quote = quoteService.getQuoteById(1).orElse(null);

        assertNotNull(quote);
        assertEquals(1, quote.id());
        assertEquals("Theodore Roosevelt", quote.author());
    }

    @Test
    void shouldReturnEmptyForInvalidQuoteId() {

        assertTrue(quoteService.getQuoteById(100).isEmpty());
    }

    @Test
    void shouldAddNewQuote() {

        Quote quote = quoteService.addQuote(
                "Great things take time.",
                "Unknown"
        );

        assertEquals(6, quote.id());
        assertEquals("Great things take time.", quote.text());
        assertEquals("Unknown", quote.author());
    }
}
