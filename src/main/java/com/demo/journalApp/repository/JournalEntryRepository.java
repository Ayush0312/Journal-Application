package com.demo.journalApp.repository;

import com.demo.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntry , ObjectId> {

}






// controller  ----> service -----> repository ------> interact with database by extending database class
