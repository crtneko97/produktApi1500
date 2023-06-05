package com.example.produktapi.repository;

import com.example.produktapi.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    public ProductRepository productRepository;


        @Test
        void findAllCateGories_ReturnTrue(){

            String[] categorys = {"electronics", "jewelery", "men's clothing", "women's clothing"};

            List <String> cGory = productRepository.findAllCategories();

            Assertions.assertAll(
                    () -> assertFalse(cGory.isEmpty()),
                    () -> assertEquals(4, cGory.size()), // Den funka inte på 3 vilket jag fann lite skummt, i.om att man räknar från 0 som där nere.
                    () -> assertEquals(categorys[0], cGory.get(0)),
                    () -> assertEquals(categorys[1], cGory.get(1)),
                    () -> assertEquals(categorys[2], cGory.get(2)),
                    () -> assertEquals(categorys[3], cGory.get(3))
            );
        }

        @Test
        void findByCategory_thenTrue(){

            String category = "computerParts";

            Product product1 = new Product(
                    "Title",
                    200.0,
                    category,
                    "desc",
                    "url");

            productRepository.save(product1);
            List<Product> optionalProduct = productRepository.findByCategory(category);

            Assertions.assertAll(
                    () -> assertFalse(optionalProduct.isEmpty()),
                    () -> assertEquals(category, optionalProduct.get(0).getCategory())
            );

        }

}