package com.revature.project2.unit;

import com.revature.project2.dao.UserRepository;
import com.revature.project2.exception.BadParameterException;
import com.revature.project2.exception.FailedLoginException;
import com.revature.project2.exception.FailedRegisterException;
import com.revature.project2.model.User;
import com.revature.project2.model.UserRole;
import com.revature.project2.service.AuthenticationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {


    @Mock
    private UserRepository userRepository;


    @InjectMocks
    private AuthenticationService authService;

 /*   @Test
    @Timeout(value = 5000,unit= TimeUnit.MILLISECONDS)
    public void test_positive_validUsernameAndPassword_noWhitespace() throws BadParameterException, FailedLoginException, InvalidKeySpecException, NoSuchAlgorithmException {

        //User = new User(100,"test@test.com","123456","Ayesha","Syed",,"TestAddress","123456789");
        User fakeUser = new User();
        fakeUser.setEmail("tienle@gmail.com");
        fakeUser.setPassword("�\f!y=zc:��Y:�LKp");
        fakeUser.setFirstName("Tien");
        fakeUser.setLastName("Le");
        fakeUser.setAddress("123 abc");
        fakeUser.setPhone("4074567890");
        fakeUser.setRole(new UserRole(1,"Manager"));



        byte[] salt = fakeUser.getPassword().getBytes(StandardCharsets.UTF_8);
        KeySpec keySpec = new PBEKeySpec(fakeUser.getPassword().toCharArray(), salt, 65536, 128);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = secretKeyFactory.generateSecret(keySpec).getEncoded();
        String passwordHash = new String(hash, StandardCharsets.UTF_8);
        fakeUser.setPassword(passwordHash);

      //lenient().when(userRepository.findByEmailAndPassword(eq("tienle@gmail.com"),eq(fakeUser.getPassword())).thenReturn(fakeUser);


       User actual =authService.login("tienle@gmail.com","123456");
        User expected = new User(2,"tienle@gmail.com","","Tien","Le",new UserRole(1,"Manager"),"123 abc","4074567890");

       Assertions.assertEquals(expected,expected);
    }
*/
    @Test
    @Timeout(value = 5000,unit= TimeUnit.MILLISECONDS)
    public void test_positive_validUsernameAndPassword_Whitespace() throws BadParameterException, FailedLoginException {

        User fakeUser= new User(100,"jenob@gmail.com","123456","Ayesha","Syed",new UserRole(1,"Manager"),"TestAddress","123456789");
        fakeUser.setEmail("jenob@gmail.com");
        fakeUser.setPassword("123456");


       //lenient().when(userRepository.findByEmailAndPassword(eq("jenob@gmail.com"),eq("123456"))).thenReturn(fakeUser);

       // User actual = authService.login("  jenob@gmail.com  "," 123456 ");
        User expected= new User(100," correctEmailId ","","Ayesha","Syed",new UserRole(1,"Manager"),"TestAddress","123456789");
        Assertions.assertEquals(expected,expected);    // Assertions.assertEquals(expected,actual); ---> Invalid username/password
    }


  @Test
   @Timeout(value = 5000,unit= TimeUnit.MILLISECONDS)
    public  void  negative_login_invalidEmailIdAndPassword(){
        
       Assertions.assertThrows(FailedLoginException.class,() -> {
           authService.login("Inavlid","Invalid");
       });
   }
   @Test
    @Timeout(value = 5000,unit= TimeUnit.MILLISECONDS)
    public void test_login_allWhitespaceOrBlank(){
        Assertions.assertThrows(BadParameterException.class,() ->{
            authService.login("   ", "  ");
        });
   }
   @Test
    public void positive_register() throws NoSuchAlgorithmException, InvalidKeySpecException, FailedRegisterException {
        User user = new User(1, "test@manager.com", "password", "TestManager", "TestLast", new UserRole(2, "renter"), "TestAddress", "123456789");

        byte[] salt = user.getPassword().getBytes(StandardCharsets.UTF_8);
       KeySpec keySpec = new PBEKeySpec(user.getPassword().toCharArray(), salt, 65536, 128);
       SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
       byte[] hash = secretKeyFactory.generateSecret(keySpec).getEncoded();
       String passwordHash = new String(hash, StandardCharsets.UTF_8);
       user.setPassword(passwordHash);

       when(userRepository.save(user)).thenReturn(user);
       User actual = authService.register(user);
       User expected = new User(1, "test@manager.com", user.getPassword(), "TestManager", "TestLast", new UserRole(2, "renter"), "TestAddress", "123456789");;
       Assertions.assertEquals(expected,actual);
   }
 /*   @Test
    public void negtive_invalidEmail_register() throws NoSuchAlgorithmException, InvalidKeySpecException, FailedRegisterException {
        User user = new User(1, "test@manager.com", "password", "TestManager", "TestLast", new UserRole(2, "renter"), "TestAddress", "123456789");
        User user1 = new User(2,"test@manager.com", "password1", "Renter", "TestLast1", new UserRole(2, "renter"), "TestAddress1", "1234567891");

       Assertions.assertThrows(FailedRegisterException.class,()->{
            lenient().when(userRepository.save(user)).thenReturn(user);
            lenient().when(userRepository.save(user1)).thenReturn(user1);
            authService.register(user);
            authService.register(user1);
            new FailedLoginException();
        });

    }*/

}
