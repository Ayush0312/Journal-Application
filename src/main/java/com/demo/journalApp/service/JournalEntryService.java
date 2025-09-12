package com.demo.journalApp.service;

import com.demo.journalApp.entity.JournalEntry;
import com.demo.journalApp.entity.User;
import com.demo.journalApp.repository.JournalEntryRepository;
import com.demo.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

@Service
@Slf4j
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public JournalEntry saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            if (user == null) {
                throw new RuntimeException("User not found!");
            }

            journalEntry.setDate(LocalDateTime.now());
            JournalEntry savedEntry = journalEntryRepository.save(journalEntry);
            System.out.println("Saved Entry"+savedEntry);


            user.getJournalEntries().add(savedEntry);
            userService.saveUser(user);

            return savedEntry;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }


    public void saveEntry(JournalEntry journalEntry){
        try{
            journalEntryRepository.save(journalEntry);
        }
        catch (Exception e){
            log.error("Exception", e);
        }

    }
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id , String userName){
        boolean removed = false;

        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured while deleting the entry.",e);
        }
        return removed;
    }
}
