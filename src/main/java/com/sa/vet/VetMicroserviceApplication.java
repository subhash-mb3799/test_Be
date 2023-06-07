package com.sa.vet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.sa.vet.mapper.DashBoardMapper;
import com.sa.vet.mapper.DashBoardMapperImpl;
import com.sa.vet.mapper.VetMapper;
import com.sa.vet.mapper.VetMapperImpl;  
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.servers.Server;
//import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.OpenAPI;  
import io.swagger.v3.oas.models.servers.Server;

  
//@OpenAPIDefinition( 
//    servers = {
//       @Server(url = "/", description = "Default Server URL")
//    }
//) 

@SpringBootApplication
public class VetMicroserviceApplication {
    
	
	@Value(value = "${swagger.url}")   
	public String url;
	
	public static void main(String[] args) {
		SpringApplication.run(VetMicroserviceApplication.class, args);
	}

	// Generate Mapper bean for Dto class
	@Bean
	public DashBoardMapper getMapper() {
		return new DashBoardMapperImpl();
	}

	// Generate Mapper bean for Dto class
	@Bean
	public VetMapper getvetMapper() {
		return new VetMapperImpl();
	}

	// Generate RestTemplate bean  
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	
	@Bean  
	public OpenAPI customOpenAPI()
	{   
		Server productionserver = new Server(); 
	//Server localserver = new Server();   
	List<Server> servers = new ArrayList<>();
	productionserver.setUrl(url);   
	//localserver.setUrl(localURL);
	servers.add(productionserver);
	// servers.add(localserver); 
	OpenAPI openAPI = new OpenAPI();    
	openAPI.setServers(servers);     
	return openAPI; 
	}
	   

}
