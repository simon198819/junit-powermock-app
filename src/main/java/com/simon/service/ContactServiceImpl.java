package com.simon.service;

import java.util.List;

import com.simon.dao.ContactDao;
import com.simon.exception.NoDataFoundException;
import com.simon.model.Contact;
import com.simon.util.EmailUtils;

public class ContactServiceImpl implements ContactService{
	
	//Our ContactServiceImpl want to Contact Dao layer
	private ContactDao dao;
	
	private EmailUtils emailUtils;

	//alt+shift+S+R
	//Now we are Injecting dao to our ContactServiceImpl through Setter Injection
	public void setDao(ContactDao dao) {
		this.dao = dao;
	}
	
	public void setEmailUtils(EmailUtils emaiUtils) {
		this.emailUtils=emaiUtils;
		
	}

	public Long getContactNumByName(String name) {
	     Long number =dao.findContactNumByName(name);
		return number;
	}

	public List<Contact> getAllContacts() {
		List<Contact> contact= dao.findAll();
		if(contact == null) {
			throw new NoDataFoundException();
		}
		return contact;
	}

	public List<Contact> getInActiveRecords() {
		List<Contact> contacts = dao.findInActiveRecords();
		if(contacts == null) {
			throw new NoDataFoundException();
		}
		return contacts;
	}

	public boolean insertContact(Contact c) {
		boolean isSaved = dao.saveContact(c);
		boolean isSent = false;
		if(isSaved) {
			isSent = emailUtils.sendEmail(c.getContactEmail());
		}
		return isSent;
	}

	



}
