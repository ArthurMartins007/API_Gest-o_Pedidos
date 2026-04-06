package com.example.service;

import com.example.entity.Cliente;
import com.example.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("Já existe um cliente cadastrado com este e-mail.");
        }
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(UUID id) {
        // o optional é pq o findbyid retorna o tipo de obj como optional
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            return clienteOptional.get();
        } else {
            throw new RuntimeException("Cliente não encontrado!");
        }
    }

    public Cliente atualizar(UUID id, Cliente clienteAtualizado) {
        Cliente clienteExistente = buscarPorId(id);
        clienteExistente.setNome(clienteAtualizado.getNome());
        //vendo se ja tem um email igual
        if (!clienteExistente.getEmail().equals(clienteAtualizado.getEmail()) &&
                clienteRepository.existsByEmail(clienteAtualizado.getEmail())) {
            throw new RuntimeException("Este e-mail já está em uso por outro cliente.");
        }
        clienteExistente.setEmail(clienteAtualizado.getEmail());

        return clienteRepository.save(clienteExistente);
    }

    public void deletar(UUID id) {
        Cliente cliente = buscarPorId(id);
        clienteRepository.delete(cliente);
    }
}