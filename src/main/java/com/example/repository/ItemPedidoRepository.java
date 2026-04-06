package com.example.repository;

import com.example.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ItemPedidoRepository extends JpaRepository <ItemPedido , UUID >  {
}
