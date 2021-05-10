package service;

import dao.InterfaceAddressDao;
import dao.InterfaceEmployeeDao;
import entity.Address;
import entity.Employee;
import entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    private InterfaceEmployeeDao interfaceEmployeeDao;

    @Autowired
    public void setInterfaceEmployeeDao(InterfaceEmployeeDao interfaceEmployeeDao) {
        this.interfaceEmployeeDao = interfaceEmployeeDao;
    }

    @Transactional
    public Employee getEmployeeByEmployeeId(Integer id) {
        return interfaceEmployeeDao.getEmployeeByEmployeeId(id);
    }
}
