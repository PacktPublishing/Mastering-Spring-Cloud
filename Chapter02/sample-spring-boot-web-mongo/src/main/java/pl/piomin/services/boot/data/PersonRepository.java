package pl.piomin.services.boot.data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import pl.piomin.services.boot.model.Person;

public interface PersonRepository extends MongoRepository<Person, String> {

	public List<Person> findByLastName(String lastName);
	public List<Person> findByAgeGreaterThan(int age);
	
}
