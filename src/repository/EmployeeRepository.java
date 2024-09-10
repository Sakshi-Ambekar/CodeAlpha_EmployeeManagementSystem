package repository;

import model.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeRepository {
    private Map<String, Employee> employeeDatabase;

    
    public EmployeeRepository() {
        this.employeeDatabase = new HashMap<>();

        //sample data
        employeeDatabase.put("E001", new Employee("E001", "John Doe", 10, 8));
        employeeDatabase.put("E002", new Employee("E002", "Jane Smith", 5, 12));
        employeeDatabase.put("E003", new Employee("E003", "Emily Johnson", 7, 10));
    }

    
    public Employee getEmployeeById(String employeeId) {
        return employeeDatabase.get(employeeId);
    }

    
    public void addEmployee(Employee employee) {
        employeeDatabase.put(employee.getEmployeeId(), employee);
    }

   
    public Map<String, Employee> getAllEmployees() {
        return employeeDatabase;
    }
}

