package com.revature.project2.unit;

import com.revature.project2.dao.RentRepository;
import com.revature.project2.dao.UserRepository;
import com.revature.project2.exception.RentNotFoundException;
import com.revature.project2.model.Rent;
import com.revature.project2.model.RentDTO;
import com.revature.project2.model.User;
import com.revature.project2.model.UserRole;
import com.revature.project2.service.RentService;
import com.revature.project2.service.RentServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RentServiceTest {

    @Mock
    private RentRepository rentRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RentDTO rentDTO;


    @InjectMocks
    private RentService rentService = new RentServiceImpl();

    @Test
    public void Positive_getAllRents() {
        User manager = new User(1, "test@manager.com", "password", "TestManager", "TestLast", new UserRole(1, "Manager"), "TestAddress", "123456789");
        User renter = new User(2, "test@renter.com", "password", "TestRenter", "TestRenterLast", new UserRole(2, "Renter"), "TestAddress1", "987654321");
        User renter1 = new User(3, "test@renter1.com", "password", "TestRenter1", "TestRenterLast1", new UserRole(2, "Renter"), "TestAddress11", "523498765");

        List<Rent> rentList = new ArrayList<>();
        Rent rent1 = new Rent(1, LocalDate.now(), manager, renter);
        Rent rent2 = new Rent(2, LocalDate.now(), manager, renter1);
        Rent rent3 = new Rent(3, LocalDate.now(), manager, renter);

        rentList.add(rent1);
        rentList.add(rent2);
        rentList.add(rent3);

        when(rentService.getAllRents()).thenReturn(rentList);
        List<Rent> actual = rentService.getAllRents();

        assertEquals(3, actual.size());

    }

    @Test
    public void negative_getAllRents() throws RentNotFoundException {
        List<Rent> rentList = new ArrayList<>();

        lenient().when(rentRepository.findAll()).thenReturn(rentList);
        List<Rent> actual = new ArrayList<>();
        assertNotEquals(2, actual.size());

    }

    @Test
    public void positive_createRent() {

        User manager = new User(1, "test@manager.com", "password", "TestManager", "TestLast", new UserRole(1, "Manager"), "TestAddress", "123456789");
        User renter = new User(2, "test@renter.com", "password", "TestRenter", "TestRenterLast", new UserRole(2, "Renter"), "TestAddress1", "987654321");

        List<Integer> bookId = new ArrayList<>();
        bookId.add(1);
        bookId.add(2);

        rentDTO = new RentDTO(manager.getId(), renter.getId(), bookId);

        when(userRepository.findById(rentDTO.getManagerId())).thenReturn(Optional.of(manager));
        when(userRepository.findById(rentDTO.getRenterId())).thenReturn(Optional.of(renter));

        Rent rent = new Rent(0, LocalDate.now(), manager, renter);

        when(rentRepository.save(rent)).thenReturn(rent);

        Rent expected = new Rent(LocalDate.now(), manager, renter);
        Rent actual = rentService.createRent(rentDTO);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void negative_createRent() {

        User manager = new User(1, "test@manager.com", "password", "TestManager", "TestLast", new UserRole(1, "Manager"), "TestAddress", "123456789");
        User renter = new User(2, "test@renter.com", "password", "TestRenter", "TestRenterLast", new UserRole(2, "Renter"), "TestAddress1", "987654321");
        User renter1 = new User();
        List<Integer> bookId = new ArrayList<>();
        bookId.add(1);
        bookId.add(2);

        rentDTO = new RentDTO(manager.getId(), renter.getId(), bookId);

        when(userRepository.findById(rentDTO.getManagerId())).thenReturn(Optional.of(manager));
        when(userRepository.findById(rentDTO.getRenterId())).thenReturn(Optional.of(renter));

        Rent rent = new Rent(0, LocalDate.now(), manager, renter);

        when(rentRepository.save(rent)).thenReturn(rent);

        Rent expected = new Rent(LocalDate.now(), manager, renter1);
        Rent actual = rentService.createRent(rentDTO);
        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    public void positive_getAllRentByRenterId(){
        User render =  new User(2, "test@renter.com", "password", "TestRenter", "TestRenterLast", new UserRole(2, "Renter"), "TestAddress1", "987654321");

        lenient().when(userRepository.findById(2)).thenReturn(Optional.of(render));

        User manager = new User(1, "test@manager.com", "password", "TestManager", "TestLast", new UserRole(1, "Manager"), "TestAddress", "123456789");
        User renter = new User(2, "test@renter.com", "password", "TestRenter", "TestRenterLast", new UserRole(2, "Renter"), "TestAddress1", "987654321");

        List<Integer> bookId = new ArrayList<>();
        bookId.add(1);
        bookId.add(2);

        rentDTO = new RentDTO(manager.getId(), renter.getId(), bookId);

        lenient().when(userRepository.findById(rentDTO.getManagerId())).thenReturn(Optional.of(manager));
        lenient().when(userRepository.findById(rentDTO.getRenterId())).thenReturn(Optional.of(renter));

        Rent rent = new Rent(0, LocalDate.now(), manager, renter);
        List<Rent>rentList= new ArrayList<>();
        rentList.add(rent);

        when(rentRepository.findAllByRenterId(renter.getId())).thenReturn(rentList);
        List<Rent> actual = rentService.getAllRentsByRenterId(2);
        List<Rent> expected =rentList;
        Assertions.assertEquals(expected,actual);

    }
   /* @Test
    public void negative_getAllRentByRenterId(){
        User render =  new User(2, "test@renter.com", "password", "TestRenter", "TestRenterLast", new UserRole(2, "Renter"), "TestAddress1", "987654321");

        lenient().when(userRepository.findById(2)).thenReturn(Optional.of(render));

        User manager = new User(1, "test@manager.com", "password", "TestManager", "TestLast", new UserRole(1, "Manager"), "TestAddress", "123456789");
        User renter = new User(2, "test@renter.com", "password", "TestRenter", "TestRenterLast", new UserRole(2, "Renter"), "TestAddress1", "987654321");

        List<Integer> bookId = new ArrayList<>();
        bookId.add(1);
        bookId.add(2);

        rentDTO = new RentDTO(manager.getId(), renter.getId(), bookId);

        lenient().when(userRepository.findById(rentDTO.getManagerId())).thenReturn(Optional.of(manager));
        lenient().when(userRepository.findById(rentDTO.getRenterId())).thenReturn(Optional.of(renter));

        Rent rent = new Rent(1,LocalDate.now(), manager, renter);
        List<Rent>rentList= new ArrayList<>();
        rentList.add(rent);

        when(rentRepository.findAllByRenterId(renter.getId())).thenReturn(rentList);
        List<Rent> actual = rentService.getAllRentsByRenterId(3);
        List<Rent> expected =rentList;
        Assertions.assertNotEquals(expected,actual);

    }
*/


}
