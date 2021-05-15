package com.example.demo.dao.implementation;

import com.example.demo.dao.InterfaceAddressDao;
import com.example.demo.entity.Address;
import com.example.demo.entity.Person;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDao extends AbstractHibernateDao<Address> implements InterfaceAddressDao {
    public AddressDao() { setClazz(Address.class); }

    // Given person ID, get the person's address line 1
    public String getAddressLine1ByPersonId(Integer personId) {
        // Get current session
        Session session = getCurrentSession();

        // Get the address line 1 in the database where person ID = personId
        Query query = session.createQuery("SELECT addressLine1 FROM Address WHERE personId = :personId");
        query.setParameter("personId", personId);

        // Save the result
        String addressLine1 = (String) query.uniqueResult();

        // Output for confirmation
        System.out.println("Address line 1 obtained");
        return addressLine1;
    }

    // Given person ID, get the person's address line 2
    public String getAddressLine2ByPersonId(Integer personId) {
        // Get current session
        Session session = getCurrentSession();

        // Get the address line 1 in the database where person ID = personId
        Query query = session.createQuery("SELECT addressLine2 FROM Address WHERE personId = :personId");
        query.setParameter("personId", personId);

        // Save the result
        String addressLine2 = (String) query.uniqueResult();

        // Output for confirmation
        System.out.println("Address Line 2 obtained");
        return addressLine2;
    }

    // Given the person ID, get the city the person is living in
    public String getCityByPersonId(Integer personId) {
        // Get current session
        Session session = getCurrentSession();

        // Get the address line 1 in the database where person ID = personId
        Query query = session.createQuery("SELECT city FROM Address WHERE personId = :personId");
        query.setParameter("personId", personId);

        // Save the result
        String city = (String) query.uniqueResult();

        // Output for confirmation
        System.out.println("City name obtained");
        return city;
    }

    // Given the person ID, get the zipcode of the city the person is living in
    public String getZipcodeByPersonId(Integer personId) {
        // Get current session
        Session session = getCurrentSession();

        // Get the address line 1 in the database where person ID = personId
        Query query = session.createQuery("SELECT zipcode FROM Address WHERE personId = :personId");
        query.setParameter("personId", personId);

        // Save the result
        String zipcode = (String) query.uniqueResult();

        // Output for confirmation
        System.out.println("Zipcode obtained");
        return zipcode;
    }

    // Given the person ID, get the state name the person is residing
    public String getStateNameByPersonId(Integer personId) {
        // Get current session
        Session session = getCurrentSession();

        // Get the address line 1 in the database where person ID = personId
        Query query = session.createQuery("SELECT stateName FROM Address WHERE personId = :personId");
        query.setParameter("personId", personId);

        // Save the result
        String stateName = (String) query.uniqueResult();

        // Output for confirmation
        System.out.println("State name obtained");
        return stateName;
    }

    // Given the person ID, get the state abbreviation the person is residing
    public String getStateAbbrByPersonId(Integer personId) {
        // Get current session
        Session session = getCurrentSession();

        // Get the address line 1 in the database where person ID = personId
        Query query = session.createQuery("SELECT stateAbbr FROM Address WHERE personId = :personId");
        query.setParameter("personId", personId);

        // Save the result
        String stateAbbr = (String) query.uniqueResult();

        // Output for confirmation
        System.out.println("State abbreviation obtained");
        return stateAbbr;
    }

    // Given the person ID, get the person's address information
    public List<Address> getAddressListByPersonId(Integer personId) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM Address WHERE personId = :personId");
        query.setParameter("personId", personId);
        List<Address> addressList = query.list();
        return addressList;
    }

    @Override
    public void addNewAddress(Address address) {
        merge(address);
    }

    // Get a list of address that is associated with the given person ID
    public List<Address> getAddressListById(Integer id) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM Address WHERE id = :id");
        query.setParameter("id", id);
        List<Address> addressList = query.list();
        return addressList;
    }
}
