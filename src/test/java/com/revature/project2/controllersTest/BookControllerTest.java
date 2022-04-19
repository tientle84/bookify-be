package com.revature.project2.controllersTest;

import com.revature.project2.controller.BookController;
import com.revature.project2.service.AuthenticationService;
import com.revature.project2.service.BookService;
import com.revature.project2.service.BookServiceImpl;
import com.revature.project2.service.JwtService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class BookControllerTest {
    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private JwtService jwtService;
    @Mock
    private BookService bookService= new BookServiceImpl();

    @InjectMocks
    BookController bookController;

    @Autowired
    private MockMvc mockMvc;

    public BookControllerTest() {
    }

    /*   @Test
 public void getAllBooks() throws UnAuthorizedResponse, Exception {
        List<Book> bookList= new ArrayList<>();

        Book book1 = new Book(199,"1234567891234","Test Title 1","Ayesha","TestPublisher 1","23-12-1994","Test Genre 1",new BookStatus("Available"),"testUrl 1");
        Book book2 = new Book(192,"1234512391234","Test Title 2","Subhana","TestPublisher 2","10-10-1995","Test Genre 2",new BookStatus("Available"),"testUrl 2");
        Book book3 = new Book(194,"1234567369234","Test Title 3","Khadija","TestPublisher 3","20-12-2020","Test Genre 3",new BookStatus("Available"),"testUrl 3");

        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);



        String headerValue ="Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0aWVubGVAZ21haWwuY29tIiwidXNlcl9pZCI6MiwidXNlcl9yb2xlIjoibWFuYWdlciJ9.gRWnz0fq-x5o39Z42yQOXVbOchJLCL-yJrfL-c83u029KdtJJgj3mrjOXvHaYbwlIwWku5oVnRWwZKOkquYJ7w";

        String token ="header={alg=HS512},body={sub=tienle@gmail.com, user_id=2, user_role=manager},signature=gRWnz0fq-x5o39Z42yQOXVbOchJLCL-yJrfL-c83u029KdtJJgj3mrjOXvHaYbwlIwWku5oVnRWwZKOkquYJ7w";
        //lenient().doReturn(token).when(jwtService.parseJwt(token));
        Mockito.when(bookService.getAllBooks()).thenReturn(bookList);*//*

        when(jwtService.parseJwt(token)).thenReturn(token);*//*


        ResponseEntity responseEntity = bookController.getAllBooks(headerValue);
        List<Book> bookList1 = (List<Book>) responseEntity.getBody();

        assertThat(bookList1).isEqualTo(bookList);

        *//*String expectedJson = (new ObjectMapper()).writeValueAsString(bookList);

        mockMvc.perform(get("/books").accept(MediaType.APPLICATION_JSON).header("Authorization",headerValue)).andExpect(status().is(200))
                .andExpect(content().json(expectedJson));*//*
       *//* mockMvc.perform(MockMvcRequestBuilders
                .get("/books").header("Authorization",headerValue))
                .andExpect(status().is(200))
                .andExpect(content().json(expectedJson));*//*
        *//*mockMvc.perform(get("/books").header("Authorization",headerValue))
                .andExpect(status().is(200))
                .andExpect(content().json(expectedJson));*//*
    }
    @Test
    public void test_createBook() throws Exception {
        String headerValue =*//*"Bearer "+*//*"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqZW5vYkBnbWFpbC5jb20iLCJ1c2VyX2lkIjoxLCJ1c2VyX3JvbGUiOiJtYW5hZ2VyIn0.zjCskbGTSiEyFlDRZTrWC9O8Il66ohMNPNsGg7OdbZ8cEMMN90CZjoGA9Tc0uQKqiLl9uHg4P8g9nZhkg4sqIQ";
        Book book = new Book();
        book.setIsbn("1234567891234");
        book.setPublishDate("23-12-1994");
        book.setPublisher("TestPublisher 1");
        book.setImageUrl("testUrl 1");
        book.setGenre("Test Genre 1");
        book.setTitle("Test Title 1");
        book.setAuthor("Ayesha");

        String jsonDto = (new ObjectMapper()).writeValueAsString(book);
        book.setId(1);
        Book expected = new Book(1,"1234567891234","Test Title 1","Ayesha","TestPublisher 1","23-12-1994","Test Genre 1",new BookStatus("Available"),"testUrl 1");
        String expectedJsonDto = (new ObjectMapper()).writeValueAsString(expected);
      //  when(bookService.createBook(book)).thenReturn(book);

        mockMvc.perform(
                        post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonDto).header("Authorization",headerValue)
                        .accept(MediaType.APPLICATION_JSON).contentType(jsonDto))
                .andExpect(status().is(200))
                .andExpect(content().json(expectedJsonDto));



    }

*/

}
