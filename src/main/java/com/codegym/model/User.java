package com.codegym.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User implements Validator {
    private String firstName;


    private String lastName;


    private String number;

    private int age;

    private String email;

    public User(String firstName, String lastName, String number, int age,@Email String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.age = age;
        this.email = email;
    }

    public User() {
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String number = user.getNumber();
        int age = user.getAge();
        String email = user.getEmail();

//        validate firstName
        ValidationUtils.rejectIfEmpty(errors, "firstName", "fN.empty");
        if (firstName.length()<5||firstName.length()>45){
            errors.rejectValue("firstName","fN.length");
        }

//        validate lastName
        ValidationUtils.rejectIfEmpty(errors, "lastName", "lN.empty");
        if (lastName.length()<5||lastName.length()>45){
            errors.rejectValue("lastName","lN.length");
        }

//        validate number
        ValidationUtils.rejectIfEmpty(errors, "number", "number.empty");
        if (number.length() > 11 || number.length() < 10) {
            errors.rejectValue("number", "number.length");
        }
        if (!number.startsWith("0")) {
            errors.rejectValue("number", "number.startsWith");
        }
        if (!number.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("number", "number.matches");
        }

//        validate age
        ValidationUtils.rejectIfEmpty(errors,"age","age.empty");
        if (age<18||age>110){
            errors.rejectValue("age","age.size");
        }

//         validate email
        ValidationUtils.rejectIfEmpty(errors,"email","email.empty");
        if (!email.matches("(^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$)")){
            errors.rejectValue("email","email.matches");
        }
    }

}
