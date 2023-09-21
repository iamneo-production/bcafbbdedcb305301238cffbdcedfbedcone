package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class SpringappApplicationTests {

	@Autowired
    private MockMvc mockMvc;

    
	@Test
	public void testPostData() throws Exception {
		String st = "{\"userId\":1,\"userName\": \"rahul\",\"userAge\": \"29\",\"userRole\": \"Admin\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(st)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
	}
	
	@Test
	public void testUpdateData() throws Exception {
		String st = "{\"userId\":1,\"userName\": \"ramesh\",\"userAge\": \"29\",\"userRole\": \"Admin\"}";
		mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(st)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
	}
	

    @Test
    public void testGetByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$").isArray())
                .andReturn();
    }

    @Test
	void testDelete() throws Exception{	

		 mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
		 				.accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(jsonPath("$").value(true))
						.andReturn();
	}

    private void checkClassExists(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " does not exist.");
        }
    }

    private void checkFieldExists(String className, String fieldName) {
        try {
            Class<?> clazz = Class.forName(className);
            clazz.getDeclaredField(fieldName);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            fail("Field " + fieldName + " in class " + className + " does not exist.");
        }
    }


	private void checkAnnotationExists(String className, String annotationName) {
		try {
			Class<?> clazz = Class.forName(className);
			ClassLoader classLoader = clazz.getClassLoader();
			Class<?> annotationClass = Class.forName(annotationName, false, classLoader);
			assertNotNull(clazz.getAnnotation((Class) annotationClass)); // Use raw type
		} catch (ClassNotFoundException | NullPointerException e) {
			fail("Class " + className + " or annotation " + annotationName + " does not exist.");
		}
	}
	

	 @Test
   public void testUserControllerClassExists() {
       checkClassExists("com.examly.springapp.controller.UserController");
   }

   @Test
   public void testUserRepoClassExists() {
       checkClassExists("com.examly.springapp.repository.UserRepo");
   }

   @Test
   public void testUserServiceClassExists() {
       checkClassExists("com.examly.springapp.service.UserService");
   }

   @Test
   public void testUserModelClassExists() {
       checkClassExists("com.examly.springapp.model.User");
   }

   @Test
   public void testUserModelHasIdField() {
       checkFieldExists("com.examly.springapp.model.User", "userId");
   }

   @Test
   public void testUserModelHasUserNameField() {
       checkFieldExists("com.examly.springapp.model.User", "userName");
   }

   @Test
   public void testUserModelHasUserAgeForField() {
       checkFieldExists("com.examly.springapp.model.User", "userAge");
   }

   @Test
   public void testUserModelHasUserRoleForField() {
       checkFieldExists("com.examly.springapp.model.User", "userRole");
   }

   
   @Test
   public void testModelHasEntityAnnotation() {
       checkAnnotationExists("com.examly.springapp.model.User", "javax.persistence.Entity");
   }

   @Test
   public void testRepoHasRepositoryAnnotation() {
       checkAnnotationExists("com.examly.springapp.repository.UserRepo", "org.springframework.stereotype.Repository");
   }
   
   @Test
   public void testServiceHasServiceAnnotation() {
       checkAnnotationExists("com.examly.springapp.service.UserService", "org.springframework.stereotype.Service");
   }
   
   @Test
   public void testControllerHasRestControllerAnnotation() {
       checkAnnotationExists("com.examly.springapp.controller.UserController", "org.springframework.web.bind.annotation.RestController");
   }
   
   @Test 
   public void testControllerFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/controller"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }
   

	@Test 
   public void testModelFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/model"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }
   

	@Test 
   public void testRepositoryFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/repository"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }
   

	@Test 
   public void testServiceFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/service"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }

}
