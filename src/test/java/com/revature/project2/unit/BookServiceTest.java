package com.revature.project2.unit;

import com.revature.project2.dao.BookRepository;
import com.revature.project2.exception.BadParameterException;
import com.revature.project2.exception.BookNotFoundException;
import com.revature.project2.exception.FailedDeleteException;
import com.revature.project2.model.Book;
import com.revature.project2.model.BookStatus;
import com.revature.project2.model.UserRole;
import com.revature.project2.service.BookService;
import com.revature.project2.service.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService = new BookServiceImpl();



    @Test
    public void positive_getBookById() throws BookNotFoundException {
        Book fakeBook = new Book();
        fakeBook.setId(200);
        fakeBook.setAuthor("Ayesha");
        fakeBook.setPublisher("TestPublisher");
        fakeBook.setTitle("Test Title");
        fakeBook.setIsbn("1234567891234");
        fakeBook.setGenre("Test Genre");
        fakeBook.setStatus(new BookStatus("Available"));
        fakeBook.setImageUrl("testUrl");
        fakeBook.setPublishDate("23-12-1994");

        UserRole userRole = new UserRole();
        userRole.setId(1);
        userRole.setRole("Librarian");

        when(bookRepository.findById(eq(200))).thenReturn(Optional.of(fakeBook));

        Book actual =bookService.getBookById(200);
        Book expected = new Book(200,"1234567891234","Test Title","Ayesha","TestPublisher","23-12-1994","Test Genre",new BookStatus("Available"),"testUrl");
        assertEquals(expected,actual);
    }
    @Test
    public void Negative_InvalidBookId_getBookById() throws BookNotFoundException {
        Book fakeBook = new Book();
        fakeBook.setId(200);
        fakeBook.setAuthor("Ayesha");
        fakeBook.setPublisher("TestPublisher");
        fakeBook.setTitle("Test Title");
        fakeBook.setIsbn("1234567891234");
        fakeBook.setGenre("Test Genre");
        fakeBook.setStatus(new BookStatus("Available"));
        fakeBook.setImageUrl("testUrl");
        fakeBook.setPublishDate("23-12-1994");

        when(bookRepository.findById(eq(200))).thenReturn(Optional.of(fakeBook));

        Book actual =bookService.getBookById(200);
        Book expected = new Book(199,"1234567891234","Test Title","Ayesha","TestPublisher","23-12-1994","Test Genre",new BookStatus("Available"),"testUrl");
        Assertions.assertNotEquals(expected,actual);
    }
    @Test
    public void Negative_NullForBookId_getBookById() throws BookNotFoundException {
        Book fakeBook = new Book();
        fakeBook.setId(200);
        fakeBook.setAuthor("Ayesha");
        fakeBook.setPublisher("TestPublisher");
        fakeBook.setTitle("Test Title");
        fakeBook.setIsbn("1234567891234");
        fakeBook.setGenre("Test Genre");
        fakeBook.setStatus(new BookStatus("Available"));
        fakeBook.setImageUrl("testUrl");
        fakeBook.setPublishDate("23-12-1994");

        when(bookRepository.findById(eq(200))).thenReturn(Optional.of(fakeBook));

        Book actual =bookService.getBookById(200);
        Book expected = new Book();
        Assertions.assertNotEquals(expected,actual);
    }
    @Test
    public void Positive_getAllBooks(){
        Book book1 = new Book(199,"1234567891234","Test Title 1","Ayesha","TestPublisher 1","23-12-1994","Test Genre 1",new BookStatus("Available"),"testUrl 1");
        Book book2 = new Book(192,"1234512391234","Test Title 2","Subhana","TestPublisher 2","10-10-1995","Test Genre 2",new BookStatus("Available"),"testUrl 2");
        Book book3 = new Book(194,"1234567369234","Test Title 3","Khadija","TestPublisher 3","20-12-2020","Test Genre 3",new BookStatus("Available"),"testUrl 3");

        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        System.out.println(bookList.size());
        when(bookService.getAllBooks()).thenReturn(bookList);
        List<Book> actual = bookService.getAllBooks();

        assertEquals(3,actual.size());

    }

    @Test
    public void negative_getAllBooks(){
        List<Book> bookList = new ArrayList<>();

        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> actual = bookService.getAllBooks();

        assertNotEquals(3,actual.size());

    }
    @Test
    public void positive_createBook() throws BookNotFoundException, BadParameterException {
        Book fakeBook = new Book();
        fakeBook.setId(200);
        fakeBook.setAuthor("Ayesha");
        fakeBook.setPublisher("TestPublisher");
        fakeBook.setTitle("Test Title");
        fakeBook.setIsbn("1234567891234");
        fakeBook.setGenre("Test Genre");
        fakeBook.setStatus(new BookStatus("Available"));
        fakeBook.setImageUrl("testUrl");
        fakeBook.setPublishDate("23-12-1994");

        when(bookRepository.save(fakeBook)).thenReturn(fakeBook);

        Book actual =bookService.createBook(fakeBook);
        Book expected = new Book(200,"1234567891234","Test Title","Ayesha","TestPublisher","23-12-1994","Test Genre",new BookStatus("Available"),"testUrl");
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void Negative_NullValues_createBook() {


       /* Book expected = new Book();*/
       Assertions.assertThrows(BadParameterException.class,()->{
           bookService.createBook(new Book("","","","","","",new BookStatus(""),""));
       });
    }
    @Test
    public void positive_deleteBookById() throws BookNotFoundException {
        Book fakeBook = new Book();
        fakeBook.setId(200);
        fakeBook.setAuthor("Ayesha");
        fakeBook.setPublisher("TestPublisher");
        fakeBook.setTitle("Test Title");
        fakeBook.setIsbn("1234567891234");
        fakeBook.setGenre("Test Genre");
        fakeBook.setStatus(new BookStatus("Available"));
        fakeBook.setImageUrl("testUrl");
        fakeBook.setPublishDate("23-12-1994");

       when(bookRepository.findById(200)).thenReturn(Optional.of(fakeBook));
       boolean actual = bookService.deleteBookById(200);
        Assertions.assertEquals(true,actual);
    }
    @Test
    public void negative_invalidBookId_deleteBookById() throws BookNotFoundException {

        Book fakeBook = new Book();
        fakeBook.setId(200);
        fakeBook.setAuthor("Ayesha");
        fakeBook.setPublisher("TestPublisher");
        fakeBook.setTitle("Test Title");
        fakeBook.setIsbn("1234567891234");
        fakeBook.setGenre("Test Genre");
        fakeBook.setStatus(new BookStatus("Available"));
        fakeBook.setImageUrl("testUrl");
        fakeBook.setPublishDate("23-12-1994");

        lenient().when(bookRepository.findById(eq(200))).thenReturn(Optional.of(fakeBook));

       Assertions.assertThrows(BookNotFoundException.class,()->{
           bookService.deleteBookById(100);
       });

    }
    @Test
    public void negative_deleteBookById() throws FailedDeleteException {

        Book fakeBook = new Book(199,"1234567891234","Test Title 1","Ayesha","TestPublisher 1","23-12-1994","Test Genre 1",new BookStatus("Not Available"),"testUrl 1");

        lenient().when(bookRepository.findById(eq(199))).thenReturn(Optional.of(fakeBook));

        Assertions.assertThrows(FailedDeleteException.class,()->{
            bookService.deleteBookById(199);
        });

    }
   @Test
   public void positive_updateBook() throws BookNotFoundException {

       Book fakeBook = new Book(200,"1234567891234","Test Title","Ayesha","TestPublisher","23-12-1994","Test Genre",new BookStatus("Available"),"testUrl");
       when(bookRepository.findById(fakeBook.getId())).thenReturn(Optional.of(fakeBook));
       Book fakeBookUpdate = new Book(200,"324514789458","Test Title","Ayesha","TestPublisher","23-12-1994","Test Genre",new BookStatus("Available"),"testUrl");
       when(bookRepository.save(fakeBookUpdate)).thenReturn(fakeBookUpdate);

       Book expected = bookService.updateBook(200,fakeBookUpdate/*new Book(200,"1234567891234","Test Title","UpdatedAyesha","TestPublisher","23-12-1994","Test Genre",new BookStatus("Available"),"testUrl")*/);

       Assertions.assertEquals("324514789458",expected.getIsbn());

   }
    @Test
    public void negative_invalidBookId_updateBook() throws BookNotFoundException {

        Book fakeBook = new Book(200,"1234567891234","Test Title","Ayesha","TestPublisher","23-12-1994","Test Genre",new BookStatus("Available"),"testUrl");
        lenient().when(bookRepository.findById(fakeBook.getId())).thenReturn(Optional.of(fakeBook));

       Assertions.assertThrows(BookNotFoundException.class, ()->{
          bookService.updateBook(10,new Book(200,"1234567891234","Test Title","Ayesha","TestPublisher","23-12-1994","Test Genre",new BookStatus("Available"),"testUrl"));
       });

    }




}
