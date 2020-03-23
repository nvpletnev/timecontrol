package ru.altagroup.timecontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.altagroup.timecontrol.dao.EmployeeDao;
import ru.altagroup.timecontrol.dao.WorkingDayDao;
import ru.altagroup.timecontrol.model.Employee;
import ru.altagroup.timecontrol.model.WorkingDay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LatenessService {

    private EmployeeDao employeeDao;
    private WorkingDayDao workingDayDao;

    @Autowired
    public LatenessService(EmployeeDao employeeDao, WorkingDayDao workingDayDao) {
        this.employeeDao = employeeDao;
        this.workingDayDao = workingDayDao;
    }

    public List<Employee> findUsersWithLateness() {
        Map<Integer, List<WorkingDay>> latenessDays = workingDayDao.findLateness(7);
        Map<Integer, Employee> employees = employeeDao.findAll();
        List<Employee> employeeList = new ArrayList<>();

        for (Map.Entry<Integer, List<WorkingDay>> entry : latenessDays.entrySet()) {
            Employee employee = employees.get(entry.getKey());
            employee.setWorkingDays(entry.getValue());
            employeeList.add(employee);
        }

        return employeeList;
    }
}