package com.reactive.controller;

import com.reactive.dto.BookResponse;
import com.reactive.dto.CreateBookRequest;
import com.reactive.dto.UpdateBookRequest;
import com.reactive.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/create")
    public Mono<BookResponse> createNewBook(@RequestBody CreateBookRequest request) {
        return bookService.createNewBook(request);
    }

    @GetMapping("/{bookId}")
    public Mono<BookResponse> getBookById(@PathVariable Integer bookId) {
        return bookService.getBookById(bookId);
    }

    @GetMapping("/all")
    public Flux<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PutMapping("/update/{bookId}")
    public Mono<BookResponse> updateBookById(
            @PathVariable Integer bookId, @RequestBody UpdateBookRequest request) {
        return bookService.updateBookById(bookId, request);
    }

    @DeleteMapping("/remove/{bookId}")
    public Mono<Void> deleteBookById(@PathVariable Integer bookId) {
        System.out.println("CONTROLLER HIT: " + bookId);
        return bookService.deleteBookById(bookId);
    }

    @GetMapping("/search")
    public Flux<BookResponse> searchBook(
            @RequestParam(required = false, defaultValue = "spring") String keyword
    ) {
        return bookService.search(keyword);
    }
}

