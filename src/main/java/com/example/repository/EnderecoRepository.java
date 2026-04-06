package com.example.repository;

import com.example.entity.Endereco;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Entity
public interface EnderecoRepository extends JpaRepository <Endereco , UUID>  {
}
