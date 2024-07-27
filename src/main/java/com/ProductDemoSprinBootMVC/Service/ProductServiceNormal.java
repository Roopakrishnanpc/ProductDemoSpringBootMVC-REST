package com.ProductDemoSprinBootMVC.Service;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ProductDemoSprinBootMVC.DAO.ProductDBNormal;
import com.ProductDemoSprinBootMVC.Entity.Product;

@Component
public class ProductServiceNormal {
	
	
	//System.out.println(product);
	List<Product> products=new ArrayList<>();
	@Autowired
	ProductDBNormal productDB;
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		productDB.save(product);
		//products.add(product);
	}
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productDB.getAll();
	}
	public List<Product> getProductByName(String name) {
		// TODO Auto-generated method stub
//			for(Product p:products)
//			{
//				if(p.getName().equals(name))
//				{
//					return p;
//				}
//			
//			}
//			return null;	
		return productDB.getProductByName(name);
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

