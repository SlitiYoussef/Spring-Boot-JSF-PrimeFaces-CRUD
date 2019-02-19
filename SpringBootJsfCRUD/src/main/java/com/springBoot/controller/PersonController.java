package com.springBoot.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.springBoot.model.Person;
import com.springBoot.repository.PersonRepository;

@Named
@ViewScoped
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	private Person person = new Person();

	private List<Person> persons;

	private boolean correction = false;

	@PostConstruct
	public void init() {
		setPersons(personRepository.findAll());
	}

	public void PersonSave() {
		personRepository.save(person);
		if (!isCorrection())
			persons.add(person);
		person = new Person();
		setCorrection(false);
	}

	public void personDelete(Person person) {
		personRepository.delete(person);
		persons.remove(person);
	}

	public void selectUpdate(Person person) {
		setPerson(person);
		setCorrection(true);
	}

	public void formClean() {
		person = new Person();
		correction = false;
	}

	public boolean isCorrection() {
		return correction;
	}

	public void setCorrection(boolean correction) {
		this.correction = correction;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
