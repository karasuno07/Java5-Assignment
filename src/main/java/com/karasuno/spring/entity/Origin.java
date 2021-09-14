package com.karasuno.spring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Origins")
@Data
@NoArgsConstructor
public class Origin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "origin_id")
	private int id;

	@Column(name = "nation")
	private String name;

	@OneToMany(mappedBy = "origin", cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH })
	private List<Product> products;

	public void addProduct(Product product) {
		if (products == null)
			products = new ArrayList<>();
		products.add(product);
		product.setOrigin(this);
	}

}
