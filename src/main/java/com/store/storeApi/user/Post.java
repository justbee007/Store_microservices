package com.store.storeApi.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name = "post")
public class Post {
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Id
    @GeneratedValue
    private Integer id;
    
    private String description;
    
    // Constructors
    public Post() {
    }

    public Post(String description) {
        this.description = description;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    private User user;

    // toString method (optional, but useful for debugging)
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}

