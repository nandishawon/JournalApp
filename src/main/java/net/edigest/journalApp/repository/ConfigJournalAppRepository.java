package net.edigest.journalApp.repository;

import net.edigest.journalApp.entity.ConfigJournalAppEntity;
import net.edigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {


}
