package com.kkk.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kkk.demo.api.command.CreateProductCmd;
import com.kkk.demo.api.command.DeleteProductCmd;
import com.kkk.demo.api.command.GetProductCmd;
import com.kkk.demo.api.command.GetProductsCmd;
import com.kkk.demo.api.command.ReplaceProductCmd;
import com.kkk.demo.api.req.ProductRequestParameter;
import com.kkk.demo.db1.entity.Product;

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	@Autowired
	private GetProductCmd getProductCmd;

	@Autowired
	private GetProductsCmd getProductsCmd;

	@Autowired
	private CreateProductCmd createProductCmd;

	@Autowired
	private ReplaceProductCmd replaceProductCmd;

	@Autowired
	private DeleteProductCmd deleteProductCmd;

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") String id) {
		return ResponseEntity.ok(getProductCmd.execute(id));
	}

	@GetMapping
	public ResponseEntity<List<Product>> getProducts(@ModelAttribute ProductRequestParameter param) {
		return ResponseEntity.ok(getProductsCmd.execute(param));
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product request) {
		Product product = createProductCmd.execute(request);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId())
				.toUri();

		return ResponseEntity.created(location).body(product);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> replaceProduct(@PathVariable("id") String id, @RequestBody Product request) {
		return ResponseEntity.ok(replaceProductCmd.updateProduct(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") String id) {
		deleteProductCmd.execute(id);
		return ResponseEntity.noContent().build();
	}

}
