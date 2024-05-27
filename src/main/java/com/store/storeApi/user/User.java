package com.store.storeApi.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name = "userdetails")
public class User {

	@Id
	@GeneratedValue
	@NotNull
	private int id;
	@Size(min = 2, message = "Name should have at least 2 characters")
	@NotNull
	private String name;
	@Column(name = "birth_date")
	private LocalDate birthDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	@Override
	public String toString() {
		return "User [Id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public User(int id, String name, LocalDate birthDate) {
		super();
		id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public User() {
		super();
	}
}
