package com.redhat.coolstore.productcatalog;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class ProductCatalogJPATests {

	@Autowired
	ProductRepository pr;

	@Test
	public void testFindAll() {
		List<Product> productList = pr.findAll();
		assertEquals(8, productList.size());
	}

	@Test
	public void testFindByName() {
		Product product = pr.findByName("Oculus Rift");
		assertTrue(444435L == product.getItemId());
	}

	// find out how to test the interface find by price.
//	@Test
//	public void testFindByPrice() {
//		Product product = pr.findByPrice(34.00d);
//		System.out.println("########### Product = " + product);
//		assertTrue(product.getName().equals("Red Fedora"));
//	}
	
	@Test
	public void testSaveAndDeleteProduct() {
		Product newProduct = new Product();
		newProduct.setName("Test Prod");
		newProduct.setDescription("This is a description");
		newProduct.setPrice(10.00d);	
		
		Product product = pr.save(newProduct);
		long id = product.getItemId();

		assertNotNull(pr.findOne(id));

		pr.delete(product);

		assertNull(pr.findOne(id));
	}
}
