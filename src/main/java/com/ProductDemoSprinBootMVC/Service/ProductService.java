package com.ProductDemoSprinBootMVC.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ProductDemoSprinBootMVC.DAO.ProductDB;
import com.ProductDemoSprinBootMVC.Entity.Product;


@Component
public class ProductService {
	
	
	//System.out.println(product);
	List<Product> products=new ArrayList<>();
	@Autowired
	ProductDB productDB;
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return productDB.save(product);
		//products.add(product);
	}
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productDB.findAll();
	}
	public List<Product> getProductByName(String name) {
		// TODO Auto-generated method stub
	
		return productDB.findAllByName(name);
			}
	public Product findProductByID(int id)
	{
		return productDB.findById(id).orElse(new Product("","","",0));
	}
	public Product updateProduct(int id, Product updatedProduct) {
	    // Find the existing product by ID
	    Product existingProduct = productDB.findById(id)
	        .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

	    // Update fields of the existing product with new values
	    existingProduct.setName(updatedProduct.getName());
	    existingProduct.setPlace(updatedProduct.getPlace());
	    existingProduct.setType(updatedProduct.getType());
	    existingProduct.setWarranty(updatedProduct.getWarranty());
	    // Add more fields if necessary

	    // Save and return the updated product
	    return productDB.save(existingProduct);
	}
	
	public void deleteProductByID(int id)
	{
		 productDB.deleteById(id);
	}
    public List<String> outOfWarranty() {
        List<String> outOfWarrantyProducts = new ArrayList<>();
        for (Product p : products) {
            if (p.getWarranty() <= 2020) {
                outOfWarrantyProducts.add(p.getName());
            }
        }
        return outOfWarrantyProducts;
    }
    public List<Product> particularPlace(String place) {
        List<Product> particularPlace = new ArrayList<>();
        for (Product p : products) {
            if (p.getPlace().equals(place)) {
            	particularPlace.add(p);
            }
        }
        return particularPlace;
    }
	public List<Product> getProductByWord(String name) {
		// TODO Auto-generated method stub
		String newname=name.toLowerCase();
		List<Product> getProductByWord = new ArrayList<>();
			for(Product p:products)
			{
				if((p.getName().toLowerCase()).contains(newname)||(p.getType().toLowerCase()).contains(newname)||(p.getPlace().toLowerCase()).contains(newname))
				{
					getProductByWord.add(p);
				}		
			}
			return getProductByWord;	
			}
	public void removeProduct(String name) {
	    Iterator<Product> iterator = products.iterator();
	    while (iterator.hasNext()) {
	        Product product = iterator.next();
	        if (product.getName().equalsIgnoreCase(name)) {
	            iterator.remove();
	            System.out.println("Removed product: " + product);
	        }
	    }
	}
    public void removeProductForEach(String name) {
        List<Product> productsToRemove = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                productsToRemove.add(product);
            }
        }
       
        products.removeAll(productsToRemove);
        for (Product removedProduct : productsToRemove) {
            System.out.println("Removed product: " + removedProduct);
        }
    }
		}

