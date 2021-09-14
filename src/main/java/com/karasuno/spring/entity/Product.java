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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "Products")
@Getter @Setter
@NoArgsConstructor
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int id;

	@Column(name = "product_name")
	private String name;

	@Column(name = "price")
	private long price;

	@Column(name = "discount")
	private Long discount;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "image")
	private String image;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "origin_id")
	private Origin origin;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private List<Description> descriptions;

	@OneToMany(mappedBy = "product", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private List<CartDetail> cartDetails;

	public void addCartDetail(CartDetail detail) {
		if (cartDetails == null)
			cartDetails = new ArrayList<>();
		cartDetails.add(detail);
		detail.setProduct(this);
	}

	public void addDescription(Description description) {
		if (descriptions == null) descriptions = new ArrayList<>();
		descriptions.add(description);
	}

}
