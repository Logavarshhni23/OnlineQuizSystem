package com.example.OnlineQuizSystem.repository;

import com.example.OnlineQuizSystem.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findByUserId(Long userId);

}