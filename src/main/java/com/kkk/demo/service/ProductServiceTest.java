package com.kkk.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkk.demo.all.exception.NotFoundException;
import com.kkk.demo.all.exception.UnprocessableEntityException;
import com.kkk.demo.api.req.ProductRequestParameter;
import com.kkk.demo.db1.entity.Product;
import com.kkk.demo.db1.repository.MockProductDao;

@Service
public class ProductServiceTest {

    @Autowired
    private MockProductDao productDao;

    public Product createProduct(Product request) {
        boolean isIdDuplicated = productDao.find(request.getId()).isPresent();
        if (isIdDuplicated) {
            throw new UnprocessableEntityException("The id of the product is duplicated.");
        }

        Product product = new Product();
        product.setId(request.getId());
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        return productDao.insert(product);
    }

    public Product getProduct(String id) {
        return productDao.find(id)
                .orElseThrow(() -> new NotFoundException("Can't find product."));
    }

    public Product replaceProduct(String id, Product request) {
        Product product = getProduct(id);
        return productDao.replace(product.getId(), request);
    }

    public void deleteProduct(String id) {
        Product product = getProduct(id);
        productDao.delete(product.getId());
    }

    public List<Product> getProducts(ProductRequestParameter param) {
        return productDao.find(param);
    }

}
