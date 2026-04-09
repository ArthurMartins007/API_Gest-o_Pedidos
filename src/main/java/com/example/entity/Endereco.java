package com.example.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name ="TB_ENDERECO")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String rua;
    @Column
    private String cidade;
    @Column
    private String cep;
    @ManyToOne(fetch = FetchType.LAZY) // eu so carrego o cliente quando peço eleNÃO ESQUECER
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Endereco() {
    }

    public Endereco(String rua, String cidade, String cep) {
        this.rua = rua;
        this.cidade = cidade;
        this.cep = cep;
    }

    public UUID getId() {
        return id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
