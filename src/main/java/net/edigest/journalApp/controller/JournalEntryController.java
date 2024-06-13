//package net.edigest.journalApp.controller;
//
//import net.edigest.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/_journal")   //pure class par mapping add kar dega...
//public class JournalEntryController {
//
//    private Map<Long,JournalEntry> journalEntries = new HashMap<>();  //kind of table...
//    @GetMapping()
//    public List<JournalEntry> getAll(){   //localhost:8080/journal GET
//        return new ArrayList<>(journalEntries.values());
//    }
//
//    @PostMapping()
//    public boolean createEntry(@RequestBody JournalEntry myEntry) {   ///localhost:8080/journal POST
//        journalEntries.put(myEntry.getId(),myEntry);  //ekhane id ta journal er ei id theke bose jaabe...
//        return true;
//    }
//
//    @GetMapping("id/{myId}")
//    public JournalEntry getJournalEntryById(@PathVariable long myId){
//        return journalEntries.get(myId);
//    }
//    @DeleteMapping("id/{myId}")
//    public JournalEntry deleteJournalEntryById(@PathVariable long myId){
//        return journalEntries.remove(myId);
//    }
//    @PutMapping("id/{myId}")
//    public JournalEntry updateJournalEntryById(@PathVariable long myId,@RequestBody JournalEntry myEntry){
//        return journalEntries.put(myId,myEntry);  //ekhane id ta path variable theke newa..
//    }
//}
