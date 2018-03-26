package pl.piomin.services.boot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.services.boot.model.Person;
import pl.piomin.services.boot.service.PersonCounterService;

@RestController
@RequestMapping("/person")
public class PersonController {

	private List<Person> persons = new ArrayList<>();
	
	@Autowired
	PersonCounterService counterService;
	
	@GetMapping
	public List<Person> findAll() {
		return persons;
	}
	
	@GetMapping("/{id}")
	public Person findById(@RequestParam("id") Long id) {
		return persons.stream().filter(it -> it.getId().equals(id)).findFirst().get();
	}
	
	@PostMapping
	public Person add(@RequestBody Person p) {
		p.setId((long) (persons.size()+1));
		persons.add(p);
		counterService.countNewPersons();
		return p;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@RequestParam("id") Long id) {
		List<Person> p = persons.stream().filter(it -> it.getId().equals(id)).collect(Collectors.toList());
		persons.removeAll(p);
		counterService.countDeletedPersons();
	}
	
	@PutMapping
	public void update(@RequestBody Person p) {
		Person person = persons.stream().filter(it -> it.getId().equals(p.getId())).findFirst().get();
		persons.set(persons.indexOf(person), p);
	}
	
}
