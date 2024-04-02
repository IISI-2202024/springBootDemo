package com.kkk.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkk.demo.all.exception.CustMessageException;
import com.kkk.demo.all.exception.UnprocessableEntityException;
import com.kkk.demo.api.req.ProductRequestParameter;
import com.kkk.demo.db1.entity.Product;
import com.kkk.demo.db1.repository.ProductDao;
import com.kkk.demo.db1.repository.ProductRepository;
import com.kkk.demo.db2.repository.UsrDao;
import com.kkk.demo.service.ProductService;

@Service
@Transactional(rollbackFor = Exception.class) // rollback when error
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private UsrDao usrDao;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product createProduct(Product request) {
		if (productRepository.findProductById(request.getId()) != null) {
			new UnprocessableEntityException("ID already exists");
		}
		return productRepository.save(request);
	}

	@Override
	public Product getProduct(String id) {
		productDao.find(id);
		usrDao.find(id);
		return productRepository.findProductById(id);
	}

	@Override
	public Product replaceProduct(String id, Product request) {
		return productRepository.updatePrice(id, request.getName(), request.getPrice());
	}

	@Override
	public void deleteProduct(String id) {
		// productRepository.deleteById(id);
		productRepository.delete(getProduct(id));
	}

	@Override
	public List<Product> getProducts(ProductRequestParameter param) {
		return productRepository.findByNameLike("%TT%");
	}

	/**
	 * Do save > updateOther > find
	 * 
	 * And when updateOther fail roll back only updateOther
	 * 
	 * When @Transactional is used at both(method/class) levels, the transactional
	 * configuration at the method level overrides the configuration at the class
	 * level.
	 */
	@Transactional(rollbackFor = CustMessageException.class)
	public Product processProduct(String id, Product product) {
		// productDao.save(product);
		productDao.updateOther(product); // throw CustMessageException when fail
		productDao.find(id);
		return product;
	}
}