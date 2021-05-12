package com.example.demo.service;

import com.example.demo.dao.InterfaceAddressDao;
import com.example.demo.domain.AddressDomain;
import com.example.demo.entity.Address;
import com.example.demo.exceptions.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    private InterfaceAddressDao addressDao;

    @Autowired
    public void setAddressDao(InterfaceAddressDao addressService) {
        this.addressDao = addressService;
    }

    @Transactional
    public ArrayList<AddressDomain> getAddressListById(Integer id) {
        try {
            List<Address> addressList = addressDao.getAddressListById(id);
            ArrayList<AddressDomain> addressDomains = new ArrayList<>();

            for (Address address : addressList) {
                AddressDomain domain = AddressDomain.builder()
                        .id(address.getId())
                        .addressLine1(address.getAddressLine1())
                        .addressLine2(address.getAddressLine2())
                        .city(address.getCity())
                        .zipcode(address.getZipcode())
                        .stateName(address.getStateName())
                        .stateAbbr(address.getStateAbbr())
                        .build();
                addressDomains.add(domain);
            }
            return addressDomains;
        } catch (Exception e) {
            throw new MyException(e);
        }
    }

    @Transactional
    public String getAddressLine1ByPersonId(Integer personId) {
        return addressDao.getAddressLine1ByPersonId(personId);
    }

    @Transactional
    public String getAddressLine2ByPersonId(Integer personId) {
        return addressDao.getAddressLine2ByPersonId(personId);
    }

    @Transactional
    public String getCityByPersonId(Integer personId) {
        return addressDao.getCityByPersonId(personId);
    }

    @Transactional
    public String getZipcodeByPersonId(Integer personId) {
        return addressDao.getZipcodeByPersonId(personId);
    }

    @Transactional
    public String getStateNameByPersonId(Integer personId) {
        return addressDao.getStateNameByPersonId(personId);
    }

    @Transactional
    public String getStateAbbrByPersonId(Integer personId) {
        return addressDao.getStateAbbrByPersonId(personId);
    }

    @Transactional
    public ArrayList<AddressDomain> getAddressListByPersonId(Integer personId) {
        try {
            List<Address> addressList = addressDao.getAddressListById(personId);
            ArrayList<AddressDomain> addressDomains = new ArrayList<>();

            for (Address address : addressList) {
                AddressDomain domain = AddressDomain.builder()
                        .id(address.getId())
                        .addressLine1(address.getAddressLine1())
                        .addressLine2(address.getAddressLine2())
                        .city(address.getCity())
                        .zipcode(address.getZipcode())
                        .stateName(address.getStateName())
                        .stateAbbr(address.getStateAbbr())
                        .build();
                addressDomains.add(domain);
            }
            return addressDomains;
        } catch (Exception e) {
            throw new MyException(e);
        }
    }
}
