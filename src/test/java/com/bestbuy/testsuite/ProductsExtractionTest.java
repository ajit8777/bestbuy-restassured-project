package com.bestbuy.testsuite;


import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response =  given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    // 1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is :" + limit);
        System.out.println("------------------End of Test---------------------------");
    }
    // 2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is :" + total);
        System.out.println("------------------End of Test---------------------------");
    }
    // 3. Extract the name of 5th product
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th product is :" + name);
        System.out.println("------------------End of Test---------------------------");
    }
    // 4. Extract the names of all the products
    @Test
    public void test004() {
        List<String> name = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all the products are :" + name);
        System.out.println("------------------End of Test---------------------------");
    }
    // 5. Extract the productId of all the products
    @Test
    public void test005() {
        List<String> id = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The productId of all the products are :" + id);
        System.out.println("------------------End of Test---------------------------");
    }
    // 6. Print the size of the data list
    @Test
    public void test006() {
        List<String> data = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list is :" + data.size());
        System.out.println("------------------End of Test---------------------------");
    }
    // 7. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test007() {
        List<HashMap<String, ?>> value = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the product where product name = Energizer - MAX Batteries AA (4-Pack) are :" + value);
        System.out.println("------------------End of Test---------------------------");
    }
    // 8. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test008() {
        List<HashMap<String, ?>> model = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack) is :" + model);
        System.out.println("------------------End of Test---------------------------");
    }
    // 9. Get all the categories of 8th products
    @Test
    public void test009() {
        List<HashMap<String, ?>> categories = response.extract().path("data.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all the categories of 8th products are :" + categories);
        System.out.println("------------------End of Test---------------------------");
    }
    // 10. Get categories of the store where product id = 150115
    @Test
    public void test010() {
        List<String> categories = response.extract().path("data.findAll{it.id == 150115}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories of the store where product id = 150115 is :" + categories);
        System.out.println("------------------End of Test---------------------------");
    }
    // 11. Get all the descriptions of all the products
    @Test
    public void test011() {
        List<String> description = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all the descriptions of all the products are :" + description);
        System.out.println("------------------End of Test---------------------------");
    }
    // 12. Get id of all the all categories of all the products
    @Test
    public void test012() {
        List<String> categoriesId = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The id of all the all categories of all the products are :" + categoriesId);
        System.out.println("------------------End of Test---------------------------");
    }
    // 13. Find the product names Where type = HardGood
    @Test
    public void test013() {
        List<String> names = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The product names Where type = HardGood are :" + names);
        System.out.println("------------------End of Test---------------------------");
    }
    // 14. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test014() {
        List<String> categories = response.extract().path("data.find{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack) is :" + categories.size());
        System.out.println("------------------End of Test---------------------------");
    }
    // 15. Find the createdAt for all products whose price < 5.49
    @Test
    public void test015() {
        List<String> createdAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all products whose price < 5.49 are :" + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }
    // 16. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test016() {
        List<String> name = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)” are :" + name);
        System.out.println("------------------End of Test---------------------------");
    }
    // 17. Find the manufacturer of all the products
    @Test
    public void test017() {
        List<String> manufacturer = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The manufacturer of all the products are :" + manufacturer);
        System.out.println("------------------End of Test---------------------------");
    }
    // 18. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test018() {
        List<String> images = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The image of products whose manufacturer is = Energizer are :" + images);
        System.out.println("------------------End of Test---------------------------");
    }
    // 19. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test019() {
        List<String> createdAt = response.extract().path("data.findAll{it.price > 5.99}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all categories products whose price > 5.99 are :" + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }
    // 20. Find the uri of all the products
    @Test
    public void test020() {
        List<String> url = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The uri of all the products are :" + url);
        System.out.println("------------------End of Test---------------------------");
    }
}



