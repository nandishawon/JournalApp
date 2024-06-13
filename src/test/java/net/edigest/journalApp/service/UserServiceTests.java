package net.edigest.journalApp.service;

import net.edigest.journalApp.entity.User;
import net.edigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testAdd() {
        assertEquals(4, 2 + 2);
    }

    @Disabled
    @Test
    public void testFindByUserName() {
        User user = userRepository.findByUserName("ram");
        assertNotNull(user);
        assertTrue(!user.getJournalEntries().isEmpty());

    }
    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "ram",
            "shyam",
            "jodu",
            "modhu"
    })
    public void testFindByUserName(String name) {
        assertNotNull(userRepository.findByUserName(name));

    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testFindByUserName(User user) {
        assertTrue(userService.saveNewUser(user));

    }

    @Disabled("tested")
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a, int b, int expected) {
        assertEquals(expected, a + b,"failed for: " + a +"+"+ b);
    }
}
