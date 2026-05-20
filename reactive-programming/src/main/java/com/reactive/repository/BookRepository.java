package com.reactive.repository;

import com.reactive.entity.Book;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {

    @Query("""
                SELECT * FROM book_details
                WHERE book_name ILIKE '%' || :keyword || '%'
            """)
    Flux<Book> searchBookByKeyword(String keyword);
}
