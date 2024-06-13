package net.edigest.journalApp.service;

import net.edigest.journalApp.entity.User;
import net.edigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@Disabled
@ActiveProfiles("prod")
@SpringBootTest
public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailServiceImpl userDetailService;


    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest() {
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("ayodhya").roles(new ArrayList<>()).build());
        UserDetails user = userDetailService.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }
}
