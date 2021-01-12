package com.project.cinderella.model.product.repository;

import java.util.List;

import com.project.cinderella.model.domain.Product;


public interface ProductDAO {
	public List selectAll(); //��� ��ǰ
	public List selectById(int subcategory_id); //���� ī�װ��� �Ҽӵ� ��� ��ǰ
	public Product select(int product_id); 
	public void insert(Product product);
	public void update(Product product);
	public void delete(int product_id);
}
