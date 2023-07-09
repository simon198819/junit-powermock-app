package com.simon.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import com.simon.dao.ContactDao;
import com.simon.exception.NoDataFoundException;
import com.simon.model.Contact;
import com.simon.service.ContactServiceImpl;

public class ContactServiceImplTest {
	
	@Test
	public void testGetContactNumByName() {
		ContactServiceImpl service = new ContactServiceImpl();
		
		ContactDao daoMock = mock(ContactDao.class);
		
		System.out.println(daoMock.getClass().getName());
		
		when(daoMock.findContactNumByName("Suresh"))
					.thenReturn((long) 990501155);
		
		when(daoMock.findContactNumByName("Santi"))
					.thenReturn((long) 99978654);
		
		service.setDao(daoMock);
		
		Long num = service.getContactNumByName("Suresh");
		
		Long num1 = service.getContactNumByName("Shanti");
		
		assertNotNull(num);
		assertNotNull(num1);
	}
	
	@Test(expected = NoDataFoundException.class)
	public void testGetAllContacts() {
		ContactServiceImpl service = new ContactServiceImpl();
		
		ContactDao daoMock = mock(ContactDao.class);
		
		when(daoMock.findAll()).thenReturn(null);
		
		service.setDao(daoMock);
		
		service.getAllContacts();
	}
	
	@Test(expected = NoDataFoundException.class)
	public void testGetInactiveRecords01() {
		ContactServiceImpl service = new ContactServiceImpl();
		
		ContactDao daoMock = mock(ContactDao.class);
		
		when(daoMock.findInActiveRecords())
					.thenReturn(null);
		
		service.setDao(daoMock);
		
		List<Contact> list = service.getInActiveRecords();
		
		assertNull(list);
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetInactiveRecords02() {
		ContactServiceImpl service = new ContactServiceImpl();
		
		ContactDao daoMock = mock(ContactDao.class);
		
		when(daoMock.findInActiveRecords())
					.thenThrow(new NullPointerException());
		
		service.setDao(daoMock);
		
		List<Contact> list = service.getInActiveRecords();
		
		assertNull(list);
	}
	
	@Test
	public void testGetInactiveRecords03() {
		ContactServiceImpl service = new ContactServiceImpl();
		
		ContactDao daoMock = mock(ContactDao.class);
		
		List<Contact> contactsList = new ArrayList<Contact>();
		contactsList.add(new Contact(101, "John", 88765354,"john@gmail.com"));
		contactsList.add(new Contact(102, "Tohn", 98765354,"tohn@gmail.com"));
		contactsList.add(new Contact(103, "Sohn", 78765354,"sohn@gmail.com"));
		contactsList.add(new Contact(104, "Gohn", 68765354,"gohn@gmail.com"));
		contactsList.add(new Contact(105, "Cohn", 48765354,"cohn@gmail.com"));
		
		when(daoMock.findInActiveRecords())
					.thenReturn(contactsList);
		
		service.setDao(daoMock);
		
		List<Contact> list = service.getInActiveRecords();
		
		assertNotNull(list);
	}
}
