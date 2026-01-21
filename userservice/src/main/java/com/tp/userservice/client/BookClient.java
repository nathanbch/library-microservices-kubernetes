package com.tp.userservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BookClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BOOK_SERVICE_URL = "http://bookservice:8080/books";

    public void reserveBook(Long bookId) {
        restTemplate.put(BOOK_SERVICE_URL + "/" + bookId + "/reserve", null);
    }

    public void returnBook(Long bookId) {
        restTemplate.put(BOOK_SERVICE_URL + "/" + bookId + "/return", null);
    }
}
