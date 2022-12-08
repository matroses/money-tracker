package main.java.moneytracker.model;

import javafx.util.StringConverter;

import java.util.UUID;

public class Person {
    private final UUID id;
    private String firstName, lastName;
    private boolean isDeleted = false;

    public Person(String firstName, String lastName) {
        // Generate random id
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public static StringConverter<Person> getConverter() {
        return new StringConverter<Person>() {
            @Override
            public String toString(Person person) {
                return person.getFullName();
            }

            @Override
            public Person fromString(String s) {
                return null;
            }
        };
    }
}
