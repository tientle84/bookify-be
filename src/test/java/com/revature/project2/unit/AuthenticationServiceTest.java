package com.revature.project2.unit;

import com.revature.project2.dao.UserRepository;
import com.revature.project2.exception.BadParamterException;
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

import javax.security.auth.login.FailedLoginException;

import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {


    @Mock
    //Mocks userRepository(fake object)
    private UserRepository userRepository;

    @InjectMocks
    //Instantiate the object
    //Inject any object labelled with @Mock into this object
    private AuthenticationService authService;

    @Test
    @Timeout(value = 5000,unit= TimeUnit.MILLISECONDS)
    public void test_positive_validUsernameAndPassword_noWhitespace() throws FailedLoginException, BadParamterException {



        User fakeUser= new User();
        fakeUser.setId(100);
        fakeUser.setFirst_name("test");
        fakeUser.setPhone("12345");
        fakeUser.setLast_name("testify");
        fakeUser.setAddress("toronto");
        fakeUser.setEmailId("correctEmailId");
        fakeUser.setPassword("correctPassword");

        UserRole e=new UserRole();
        e.setId(1);
        e.setUserRole("liberian");

        fakeUser.setUserRole(e);


       when(userRepository.findByEmailIdAndPassword(eq("correctEmailId"),eq("correctPassword"))).thenReturn(
               fakeUser
       );


        User actual =authService.login("correctEmailId","correctPassword");

       User expected= new User();
       expected.setId(100);
       expected.setFirst_name("test");
       expected.setLast_name("testify");
       expected.setPhone("12345");
       expected.setAddress("toronto");
       expected.setEmailId("correctEmailId");
       expected.setPassword("correctPassword");

        UserRole liberian=new UserRole();
        liberian.setId(1);
        liberian.setUserRole("liberian");

       expected.setUserRole(liberian);

        Assertions.assertEquals(expected,actual);
    }

   @Test
   @Timeout(value = 5000,unit= TimeUnit.MILLISECONDS)
    public void test_positive_validUsernameAndPassword_withWhitespace() throws FailedLoginException, BadParamterException {
       User fakeUser= new User();
       fakeUser.setId(100);
       fakeUser.setFirst_name("test");
       fakeUser.setPhone("12345");
       fakeUser.setLast_name("testify");
       fakeUser.setAddress("toronto");
       fakeUser.setEmailId("correctEmailId");
       fakeUser.setPassword("correctPassword");

       UserRole e=new UserRole();
       e.setId(1);
       e.setUserRole("liberian");

       fakeUser.setUserRole(e);


       when(userRepository.findByEmailIdAndPassword(eq("correctEmailId"),eq("correctPassword"))).thenReturn(
               fakeUser
       );

       User actual=authService.login("   correctEmailId  ","  correctPassword  ");


       User expected= new User();
       expected.setId(100);
       expected.setFirst_name("test");
       expected.setLast_name("testify");
       expected.setPhone("12345");
       expected.setAddress("toronto");
       expected.setEmailId("correctEmailId");
       expected.setPassword("correctPassword");

       UserRole liberian=new UserRole();
       liberian.setId(1);
       liberian.setUserRole("liberian");

       expected.setUserRole(liberian);

       Assertions.assertEquals(expected,actual);
   }

   @Test
   @Timeout(value = 5000,unit= TimeUnit.MILLISECONDS)
    public  void  test_login_invalidEmailIdAndPassword(){
        //
       Assertions.assertThrows(FailedLoginException.class,() -> {
           authService.login("Inavlid","Invalid");
       });
   }
   @Test
    @Timeout(value = 5000,unit= TimeUnit.MILLISECONDS)
    public void test_login_allWhitespaceOrBlank(){
        Assertions.assertThrows(BadParamterException.class,() ->{
            authService.login("   ", "  ");
        });
   }

}
