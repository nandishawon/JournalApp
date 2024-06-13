package net.edigest.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;
    @Test
    void testSendMail(){
        emailService.sendEmail("shawonnandi577@gmail.com",
                "Testing java mail sender",
                "Hi, aap kaise ho?");
    }

}
