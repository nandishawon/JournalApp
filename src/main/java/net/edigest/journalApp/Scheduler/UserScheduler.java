package net.edigest.journalApp.Scheduler;

import net.edigest.journalApp.cache.AppCache;
import net.edigest.journalApp.entity.JournalEntry;
import net.edigest.journalApp.entity.User;
import net.edigest.journalApp.enums.Sentiment;
import net.edigest.journalApp.model.SentimentData;
import net.edigest.journalApp.repository.UserRepositoryImpl;
import net.edigest.journalApp.service.EmailService;
import net.edigest.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {
    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepositoryimpl;

    @Autowired
    private AppCache appCache;

    @Autowired
    private KafkaTemplate<String, SentimentData> kafkaTemplate;


//    @Scheduled(cron = "0 * * ? * *" )
    public void fetchUserAndSendSaMail() {
        List<User> users = userRepositoryimpl.getUserForSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            if (journalEntries != null && !journalEntries.isEmpty()) {
                List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
                Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
                for (Sentiment sentiment : sentiments) {
                    if (sentiment != null) {
                        sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                    }
                }
                Sentiment mostFrequentSentiment = null;
                int maxCount = 0;
                for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                    if (entry.getValue() > maxCount) {
                        maxCount = entry.getValue();
                        mostFrequentSentiment = entry.getKey();
                    }
                }
                if (mostFrequentSentiment != null) {

                    //Without Kafka
                    //emailService.sendEmail(user.getEmail(), "sentiment for lat 7 dayts ", mostFrequentSentiment.toString());

                    //Using Kafka
                    SentimentData sentimentData = SentimentData.builder().email(user.getEmail()).sentiment("sentiment for last 7 days " + mostFrequentSentiment.toString()).build();
                    kafkaTemplate.send("weekly-sentiments",sentimentData.getEmail(),sentimentData);  //key: sentimentData.getEmail(), smme key hogi to same partition mein jaayegi....
                }

            }
        }
    }
}

