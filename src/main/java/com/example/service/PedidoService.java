package com.example.service;

import com.example.entity.ItemPedido;
import com.example.entity.Pedido;
import com.example.entity.Produto;
import com.example.entity.Status;
import com.example.repository.PedidoRepository;
import com.example.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PedidoService {

    @Autowired
    private  PedidoRepository pedidoRepository;
    @Autowired
    private  ProdutoRepository produtoRepository;


    public Pedido salvar(Pedido pedido) {
        pedido.setData(LocalDate.now());
        pedido.setStatus(Status.CRIADO);

        if (pedido.getItens() != null) {
            for (ItemPedido item : pedido.getItens()) {

                // Busca o produto no banco
                Optional<Produto> produtoOpt = produtoRepository.findById(item.getProduto().getId());
                if (produtoOpt.isEmpty()) {
                    throw new RuntimeException("Produto não encontrado!");
                }
                Produto produto = produtoOpt.get();

                // Validar disponibilidade no estoque
                if (produto.getEstoque() < item.getQuantidade()) {
                    throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
                }
                produto.setEstoque(produto.getEstoque() - item.getQuantidade());
                //coloca o item para usar o preco unitario obrigatoriiamente
                item.setPrecoUnitario(produto.getPreco());
                item.setPedido(pedido);
            }
        }

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(UUID id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);

        if (pedidoOptional.isPresent()) {
            return pedidoOptional.get();
        } else {
            throw new RuntimeException("Pedido não encontrado!");
        }
    }

    public Pedido atualizarStatus(UUID id, Status novoStatus) {
        Pedido pedido = buscarPorId(id);

        // Regra 5: Fluxo de status [cite: 92-97]
        // Não pode pular etapas e não pode voltar status
        if (pedido.getStatus() == Status.CRIADO && novoStatus != Status.PAGO) {
            throw new RuntimeException("O pedido CRIADO só pode avançar para PAGO.");
        }
        if (pedido.getStatus() == Status.PAGO && novoStatus != Status.ENVIADO) {
            throw new RuntimeException("O pedido PAGO só pode avançar para ENVIADO.");
        }
        if (pedido.getStatus() == Status.ENVIADO || pedido.getStatus() == Status.CANCELADO) {
            throw new RuntimeException("Não é possível alterar o status de um pedido já ENVIADO ou CANCELADO.");
        }

        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }

    // Corresponde ao DELETE /pedidos/{id} [cite: 114]
    public void cancelar(UUID id) {
        Pedido pedido = buscarPorId(id);

        // Regra 4: Apenas pedidos com status CRIADO podem ser cancelados [cite: 91]
        if (pedido.getStatus() != Status.CRIADO) {
            throw new RuntimeException("Apenas pedidos com status CRIADO podem ser cancelados.");
        }

        pedido.setStatus(Status.CANCELADO);

        // Opcional, mas uma boa prática: devolver o estoque dos produtos se a compra foi cancelada
        if (pedido.getItens() != null) {
            for (ItemPedido item : pedido.getItens()) {
                Produto produto = item.getProduto();
                produto.setEstoque(produto.getEstoque() + item.getQuantidade());
            }
        }

        pedidoRepository.save(pedido);
    }
}