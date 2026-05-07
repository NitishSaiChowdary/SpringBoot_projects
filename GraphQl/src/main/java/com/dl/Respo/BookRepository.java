package com.dl.Respo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dl.model.Books;

public interface BookRepository extends JpaRepository<Books, Integer>{

}
