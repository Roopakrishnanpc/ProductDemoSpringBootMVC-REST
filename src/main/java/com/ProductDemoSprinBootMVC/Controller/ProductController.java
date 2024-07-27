package com.ProductDemoSprinBootMVC.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.ProductDemoSprinBootMVC.Entity.Product;
import com.ProductDemoSprinBootMVC.Service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


//@Controller
@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productservce;
	
	//@GetMapping("/product")
	@GetMapping
	public List<Product> getMethodName() {
		return productservce.getAllProducts();
	}
	
	@GetMapping("/product/{id}")
	public Product getProductById(@PathVariable int id) {
		return productservce.findProductByID(id);
	}
	@PostMapping("/product")
	public Product addProduct(@RequestBody Product product)
	{
		return productservce.addProduct(product);
	}
	@PutMapping("product/{id}")
	public Product updateProduct(@PathVariable int id,@RequestBody Product product)
	{
		return productservce.updateProduct(id,product);
	}
	@DeleteMapping("product/{id}")
	public void deleteProductById(@PathVariable int id) {
		productservce.deleteProductByID(id);
	}
}
