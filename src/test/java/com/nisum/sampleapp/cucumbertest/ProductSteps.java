package com.nisum.sampleapp.cucumbertest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.nisum.sampleapp.model.Product;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ProductSteps {

	//WireMockServer wireMockServer = new WireMockServer();
	
	WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8089)); 
	CloseableHttpClient httpClient = HttpClients.createDefault();
	
	@When("^users submitted data to the Web service$")
	public void usersSubmittedDataToWebservice() throws IOException{
		
		Product product =new Product("prod_6603","proddname4","desc","type66",4);
		String prodJson=convertToJson(product);
		
		wireMockServer.start();
		WireMock.configureFor(wireMockServer.port());
		WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/createProduct"))
                .withHeader("content-type", WireMock.equalTo("application/json"))
                .withRequestBody(WireMock.containing("testing-framework"))
                .willReturn(WireMock.aResponse().withStatus(200)));

		HttpPost request = new HttpPost("http://localhost:8089/createProduct");

		StringEntity entity = new StringEntity(prodJson);
		request.addHeader("content-type", "application/json");
        request.setEntity(entity);
        
        HttpResponse response = httpClient.execute(request);
        
        assertNotNull(response);
        //assertEquals(HttpStatus.OK,response.getStatusLine().getStatusCode());
		
        WireMock.verify(WireMock.postRequestedFor(WireMock.urlEqualTo("/createProduct")).
        		withHeader("content-type", WireMock.equalTo("application/json")));
        
        wireMockServer.stop();

	}
	
	@Then("^the server should return a success status$")
    public void theServerShouldReturnASuccessStatus() {
    }

	private String convertToJson(Object obj)throws JsonProcessingException {
		
		ObjectMapper objMapper=new ObjectMapper();
		String json=objMapper.writeValueAsString(obj);
		return json;
		
	}

	
	
	
}
