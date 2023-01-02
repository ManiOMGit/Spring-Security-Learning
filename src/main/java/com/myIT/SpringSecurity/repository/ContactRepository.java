package com.myIT.SpringSecurity.repository;

import com.myIT.SpringSecurity.model.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {

}
