package com.generation.rcgames.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tb_categoria")
public class categoria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message="Nome não pode ser vazio")
	@Size(min=5,max=20,message="o nome deve conter entre 5 e 20 caracteres")
	private String nome;
	
	@NotBlank(message="a descrição não pode estar vazia")
	@Size(min=10,max=100,message="A descrição deve ter entre 10 e 100 caracteres")
	private String descricao;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="Categoria",cascade=CascadeType.REMOVE)
	@JsonIgnoreProperties("categoria")
	private List<produto> Produto;

	public List<produto> getProduto() {
		return Produto;
	}

	public void setProduto(List<produto> produto) {
		Produto = produto;
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


}
