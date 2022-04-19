package com.revature.project2.controllersTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project2.controller.AuthenticationController;
import com.revature.project2.model.LoginDTO;
import com.revature.project2.model.User;
import com.revature.project2.model.UserRole;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest // Spring test
@AutoConfigureMockMvc // Spring test
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthenticationController authController;

    @Test
    public void test_loginEndpoint_positive() throws Exception {
        LoginDTO dto = new LoginDTO();
        dto.setEmail("jenob@gmail.com");
        dto.setPassword("123456");
        String jsonDto = (new ObjectMapper()).writeValueAsString(dto);
        System.out.println("value" + jsonDto);

        UserRole expectedUserRole = new UserRole(1, "manager");

        User expected = new User(1, "jenob@gmail.com", "", "jenob", "job", expectedUserRole, "Toronto", "2232443434");
        String expectedJson = (new ObjectMapper()).writeValueAsString(expected);

        this.mockMvc.perform(
                        post("/login").
                                contentType(MediaType.APPLICATION_JSON).
                                content(jsonDto))
                .andExpect(status().is(200)) // 200 status code
                .andExpect(content().json(expectedJson)) // User object as JSON in the response body
                // token response header contains the correct JWT
                .andExpect(header().string("token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqZW5vYkBnbWFpbC5jb20iLCJ1c2VyX2lkIjoxLCJ1c2VyX3JvbGUiOiJtYW5hZ2VyIn0.zjCskbGTSiEyFlDRZTrWC9O8Il66ohMNPNsGg7OdbZ8cEMMN90CZjoGA9Tc0uQKqiLl9uHg4P8g9nZhkg4sqIQ"));
    }

    @Test
    public void test_loginEndpoint_invalidCredentials() throws Exception {
        LoginDTO dto = new LoginDTO();
        dto.setEmail("invalid");
        dto.setPassword("invalid");
        String jsonDto = (new ObjectMapper()).writeValueAsString(dto);

        this.mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(jsonDto))
                .andExpect(status().is(401))
                .andExpect(content().string("Invalid username and/or password."));
    }

    @Test
    public void test_loginEndpoint_blankOrWhitespaceUsernameAndPassword() throws Exception {
        LoginDTO dto = new LoginDTO();
        dto.setEmail("    ");
        dto.setPassword("");
        String jsonDto = (new ObjectMapper()).writeValueAsString(dto);

        this.mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(jsonDto))
                .andExpect(status().is(400))
                .andExpect(content().string("You must provide username and password to login."));
    }

    @Test
    @Before("")
    public void test_registerEndpoint_validCredentials() throws Exception {
        User user = new User();
        user.setEmail("ayesha@gmail.com");
        user.setPassword("123456");
        user.setFirstName("Ayesha");
        user.setLastName("Solanki");
        user.setAddress("Toronto");
        user.setPhone("123456789");

        String jsonDto = (new ObjectMapper()).writeValueAsString(user);
        User expectedUser = new User(6, "ayesha@gmail.com", "", "Ayesha", "Solanki", new UserRole(2, "renter"), "Toronto", "123456789");

        String expectedJsonDto = (new ObjectMapper()).writeValueAsString(expectedUser);
        this.mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(jsonDto))
                .andExpect(status().is(200))
                .andExpect(content().json(expectedJsonDto));


    }

    @Test
    public void test_registerEndpoint_inValidCredentials_sameEmailId() throws Exception {
        User user = new User();
        user.setEmail("jenob@gmail.com");
        user.setPassword("123456");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setAddress("Toronto");
        user.setPhone("123456789");

        String jsonDto = (new ObjectMapper()).writeValueAsString(user);
        this.mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(jsonDto))
                .andExpect(status().is(400))
                .andExpect(content().string("Email has already been registered."));


    }
}
