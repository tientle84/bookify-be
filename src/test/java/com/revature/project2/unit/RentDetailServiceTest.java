package com.revature.project2.unit;

import com.revature.project2.dao.BookRepository;
import com.revature.project2.dao.RentDetailRepository;
import com.revature.project2.dao.RentRepository;
import com.revature.project2.model.*;
import com.revature.project2.service.RentDetailService;
import com.revature.project2.service.RentDetailServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RentDetailServiceTest {

    @Mock
    private RentRepository rentRepository;
    @Mock
    private RentDetailRepository rentDetailRepository;
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private RentDetailService rentDetailService = new RentDetailServiceImpl();

    @Test
    public void positive_createRentDetail() {

        Book book = new Book(1, "1234567891234", "Test Title", "Ayesha", "TestPublisher", "23-12-1994", "Test Genre", new BookStatus("Available"), "testUrl");
        lenient().when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        BookStatus status = new BookStatus(2, "Not Available");
        Book book1 = new Book(1, "1234567891234", "Test Title", "Ayesha", "TestPublisher", "23-12-1994", "Test Genre", status, "testUrl");

        lenient().when(bookRepository.save(book)).thenReturn(book1);

        User manager = new User(1, "test@manager.com", "password", "TestManager", "TestLast", new UserRole(1, "Manager"), "TestAddress", "123456789");
        User renter = new User(2, "test@renter.com", "password", "TestRenter", "TestRenterLast", new UserRole(2, "Renter"), "TestAddress1", "987654321");

        Rent rent = new Rent(1, LocalDate.now(), manager, renter);

        RentDetail rentDetail = new RentDetail(1, rent, book, LocalDate.now(), null, 0);

        lenient().when(rentDetailRepository.save(rentDetail)).thenReturn(rentDetail);

        RentDetail actual = rentDetailService.createRentDetail(rent, 1, LocalDate.now());

        RentDetail expected = new RentDetail(0, rent, book1, LocalDate.now(),null, 0);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void negative_createRentDetail() {

        Book book = new Book(1, "1234567891234", "Test Title", "Ayesha", "TestPublisher", "23-12-1994", "Test Genre", new BookStatus("Available"), "testUrl");
        Book fakeBook = new Book(1, "987654321", "Test Title1", "Subhana", "TestPublisher1", "23-12-1994", "Test Genre1", new BookStatus("Available"), "testUrl1");
        lenient().when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        BookStatus status = new BookStatus(2, "Not Available");
        Book book1 = new Book(1, "1234567891234", "Test Title", "Ayesha", "TestPublisher", "23-12-1994", "Test Genre", status, "testUrl");

        lenient().when(bookRepository.save(book)).thenReturn(book1);

        User manager = new User(1, "test@manager.com", "password", "TestManager", "TestLast", new UserRole(1, "Manager"), "TestAddress", "123456789");
        User renter = new User(2, "test@renter.com", "password", "TestRenter", "TestRenterLast", new UserRole(2, "Renter"), "TestAddress1", "987654321");

        Rent rent = new Rent(1, LocalDate.now(), manager, renter);

        RentDetail rentDetail = new RentDetail(1, rent, book, LocalDate.now(), null, 0);

        lenient().when(rentDetailRepository.save(rentDetail)).thenReturn(rentDetail);

        RentDetail actual = rentDetailService.createRentDetail(rent, 1, LocalDate.now());

        RentDetail expected = new RentDetail(0, rent, fakeBook, LocalDate.now(),null, 0);
        Assertions.assertNotEquals(expected, actual);

    }

    @Test
    public void positive_getAllRentDetailsById(){
        Book book = new Book(1, "1234567891234", "Test Title", "Ayesha", "TestPublisher", "23-12-1994", "Test Genre", new BookStatus("Not Available"), "testUrl");
        Book fakeBook = new Book(1, "987654321", "Test Title1", "Subhana", "TestPublisher1", "23-12-1994", "Test Genre1", new BookStatus("Not AvaiLable"), "testUrl1");

        User manager = new User(1, "test@manager.com", "password", "TestManager", "TestLast", new UserRole(1, "Manager"), "TestAddress", "123456789");
        User renter = new User(2, "test@renter.com", "password", "TestRenter", "TestRenterLast", new UserRole(2, "Renter"), "TestAddress1", "987654321");

        Rent rent = new Rent(1, LocalDate.now(), manager, renter);
        when(rentRepository.findById(1)).thenReturn(Optional.of(rent));

        RentDetail rentDetail1 = new RentDetail(0, rent, fakeBook, LocalDate.now(),null, 0);
        RentDetail rentDetail2 = new RentDetail(1, rent, book, LocalDate.now(),null, 0);

        List<RentDetail> rentDetailList = new ArrayList<>();
        rentDetailList.add(rentDetail1);
        rentDetailList.add(rentDetail2);
        when(rentDetailRepository.findAllByRentId(rent.getId())).thenReturn(rentDetailList);

        List<RentDetail> actual = rentDetailService.getAllRentDetailByRentId(1);
        Assertions.assertEquals(2,actual.size());

    }
   /* @Test
    public void negative_invalidRentId_getAllRentDetailsById(){
        Book book = new Book(1, "1234567891234", "Test Title", "Ayesha", "TestPublisher", "23-12-1994", "Test Genre", new BookStatus("Not Available"), "testUrl");
        Book fakeBook = new Book(1, "987654321", "Test Title1", "Subhana", "TestPublisher1", "23-12-1994", "Test Genre1", new BookStatus("Not AvaiLable"), "testUrl1");

        User manager = new User(1, "test@manager.com", "password", "TestManager", "TestLast", new UserRole(1, "Manager"), "TestAddress", "123456789");
        User renter = new User(2, "test@renter.com", "password", "TestRenter", "TestRenterLast", new UserRole(2, "Renter"), "TestAddress1", "987654321");

        Rent rent = new Rent(1, LocalDate.now(), manager, renter);
        Rent rent1 = new Rent(2, LocalDate.now(), manager, renter);
        lenient().when(rentRepository.findById(1)).thenReturn(Optional.of(rent));

        RentDetail rentDetail1 = new RentDetail(1, rent, fakeBook, LocalDate.now(),null, 0);
        RentDetail rentDetail2 = new RentDetail(2, rent, book, LocalDate.now(),null, 0);

        List<RentDetail> rentDetailList = new ArrayList<>();
        rentDetailList.add(rentDetail1);
        rentDetailList.add(rentDetail2);
        lenient().when(rentDetailRepository.findAllByRentId(rent.getId())).thenReturn(rentDetailList);

        List<RentDetail> actual = rentDetailService.getAllRentDetailByRentId(2);
        Assertions.assertNotEquals(1,actual.size());

    }
*/

}
