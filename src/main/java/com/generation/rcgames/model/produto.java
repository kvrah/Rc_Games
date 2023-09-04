package com.generation.rcgames.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tb_produto")
public class produto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message="o nome do produto não pode ser vazio")
	@Size(min=5,max=20,message="o nome deve conter entre 5 e 20 caracteres")
	private String nome;
	
	@NotBlank(message="a descrição não pode ser vazia")
	@Size(min=10,max=100,message="a descrição deve ter entre 10 e 100 caracteres")
	private String descricao;
	
	@NotBlank(message="o preço não pode ser vazio")
	@Column(precision=6, scale=2)
	private BigDecimal preco;
	
	@NotBlank(message="a foto do produto é obrigatoria")
	private String foto;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private categoria Categoria;

	public categoria getCategoria() {
		return Categoria;
	}

	public void setCategoria(categoria categoria) {
		Categoria = categoria;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
}
