package net.edigest.journalApp.service;

import net.edigest.journalApp.model.SentimentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SentimentConsumerService {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "weekly-sentiments", groupId = "springboot-group-1")
    public void consume(SentimentData sentimentData) {
      sendKafkaEmail(sentimentData);
    }

    private void sendKafkaEmail(SentimentData sentimentData){
        emailService.sendEmail(sentimentData.getEmail(), "Sentiment for previous week", sentimentData.getSentiment());
    }

}
