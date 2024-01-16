package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response =  given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    // 1.Extract the limit
    @Test
    public void test001() {

        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is :" + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    //2. Extract the total
    @Test
    public void test002(){

        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Total is :" + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void test003(){

        String name = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The 5th Store name is :" + name);
        System.out.println("------------------End of Test---------------------------");

    }
    //4. Extract the names of all the store
    @Test
    public void test004() {

        List<String> storesName = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The All store name are :" + storesName);
        System.out.println("------------------End of Test---------------------------");

    }
    //5. Extract the storeId of all the store
    @Test
    public void test005(){

        List<Integer> storesId = response.extract().path("data.services.storeservices.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all store storeId are :" + storesId);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void test006(){

        List<Integer> data = response.extract().path("data");
        int dataSize = data.size();

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The data list size is :" + dataSize);
        System.out.println("------------------End of Test---------------------------");
    }
    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007(){

        List<HashMap<String, ?>> allValue = response.extract().path("data.findAll{it.name=='St Cloud'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all value of store is :" + allValue);
        System.out.println("------------------End of Test---------------------------");
    }
    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008(){

        List<String> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Address of Rochester store is :" + address);
        System.out.println("------------------End of Test---------------------------");
    }
    //9. Get all the services of 8th store
    @Test
    public void test009(){

        List<HashMap<String, ?>> services = response.extract().path("data[7].services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services of 8th store is :" + services);
        System.out.println("------------------End of Test---------------------------");
    }
    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010(){

        List<String> storeServices = response.extract().path("data.findAll{it.name == 'Windows Store'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices of the Windows Store is :" + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }
    //11. Get all the storeId of all the store
    @Test
    public void test011(){

        List<Integer> storeIds = response.extract().path("data.services.storeservices.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all store Ids is :" + storeIds);
        System.out.println("------------------End of Test---------------------------");
    }
    //12. Get id of all the store
    @Test
    public void test012(){

        List<Integer> ids = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all store id is :" + ids);
        System.out.println("------------------End of Test---------------------------");
    }
    //13. Find the store names Where state = ND
    @Test
    public void test013(){

        List<String> name = response.extract().path("data.findAll{it.state == 'ND'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store names Where state = ND is :" + name);
        System.out.println("------------------End of Test---------------------------");
    }
    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014(){

        List<?> totalServices = response.extract().path("data.find{it.name == 'Rochester'}.services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The data list size is :" + totalServices.size());
        System.out.println("------------------End of Test---------------------------");
    }
    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015(){

        List<String> services = response.extract().path("data.findAll{it.services.findAll{it.name == 'Windows Store'}}.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The data list size is :" + services);
        System.out.println("------------------End of Test---------------------------");
    }
    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016(){

        List<String> name = response.extract().path("data.findAll{it.name =='Fargo'}.services.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The data list size is :" + name);
        System.out.println("------------------End of Test---------------------------");
    }
    //17. Find the zip of all the store
    @Test
    public void test017(){

        List<Integer> zip = response.extract().path("data.zip");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all the store is :" + zip);
        System.out.println("------------------End of Test---------------------------");
    }
    //18. Find the zip of store name = Roseville
    @Test
    public void test018(){

        List<Integer> zip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of store name = Roseville is :" + zip);
        System.out.println("------------------End of Test---------------------------");
    }
    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019(){

        List<String> storeDetails = response.extract().path("data.findAll{it.services.findAll{it.name == 'Magnolia Home Theater'}.storeservices}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices details is :" + storeDetails);
        System.out.println("------------------End of Test---------------------------");
    }
    //20. Find the lat of all the stores
    @Test
    public void test020(){

        List<Integer> lat = response.extract().path("data.lat");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat of all the stores is :" + lat);
        System.out.println("------------------End of Test---------------------------");
    }


}
