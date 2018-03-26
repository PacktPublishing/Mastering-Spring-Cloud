package pl.piomin.services.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Service;

@Service
public class PersonCounterService {

	private final CounterService counterService;
	
    @Autowired
    public PersonCounterService(CounterService counterService) {
        this.counterService = counterService;
    }
    
    public void countNewPersons() {
    	this.counterService.increment("services.person.add");
    }
    
    public void countDeletedPersons() {
    	this.counterService.increment("services.person.deleted");
    }
    
}
