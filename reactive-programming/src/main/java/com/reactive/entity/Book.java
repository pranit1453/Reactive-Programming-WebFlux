package com.reactive.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("book_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    @Column("book_id")
    private Integer bookId;
    @Column("book_name")
    private String name;
    @Column("book_description")
    private String description;
    @Column("book_publisher")
    private String publisher;
    @Column("book_author")
    private String author;
}
