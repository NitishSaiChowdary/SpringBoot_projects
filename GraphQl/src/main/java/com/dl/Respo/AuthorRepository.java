package com.dl.Respo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dl.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
