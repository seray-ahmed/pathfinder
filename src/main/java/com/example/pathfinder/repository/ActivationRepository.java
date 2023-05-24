package com.example.pathfinder.repository;

import com.example.pathfinder.model.Activation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
    public interface ActivationRepository extends JpaRepository<Activation, String> {
}
