package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ProductsCRUDTest extends TestBase {

    static  String name = "Duracell - AAA Batteries (12-Pack)";
    static  String type = "HardGood";
    static  double price = 5.99;
    static  int shipping = 0;
    static  String upc = "041333424999";
    static  String description = "Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack";
    static  String manufacturer = "Duracell";
    static  String model = "MN1500B4Z";
    static  String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";
    static  String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";
    static long productId;


    // Create a product
    @Test
    public void test001() {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .post();
        response.prettyPrint();
        response.then().statusCode(201);
    }

    // get the product
    @Test
    public void test002(){
        String s1 = "data.findAll{it.upc == '";
        String s2 = "'}.name";

        ValidatableResponse response =
                given()
                        .when()
                        .get()
                        .then().statusCode(200);
        List<String> productName = response.extract()
                .path("data.findAll{it.id == 9999710}.name");
        response.body(name, equalTo(productName));



    }

    // Update the product
    @Test
    public void test003(){

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("Duracell - AAA Batteries (16-Pack)");

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .patch("/9999710");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    // Delete the product
    @Test
    public void test004(){
        given()
                .when()
                .delete("/9999710")
                .then()
                .statusCode(200);

        given()
                .when()
                .get("/9999710")
                .then()
                .statusCode(404);
    }
}
