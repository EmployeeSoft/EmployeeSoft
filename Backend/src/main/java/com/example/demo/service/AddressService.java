package com.example.demo.service;

import com.example.demo.dao.InterfaceAddressDao;
import com.example.demo.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AddressService {
    private InterfaceAddressDao addressDao;

    @Autowired
    public void setAddressService(InterfaceAddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Transactional
    public List<Address> addressList() {
        return addressDao.getAddressList();
    }
}
