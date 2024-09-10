package model;

public class Employee {
    private String employeeId;
    private String name;
    private int sickLeaveBalance;
    private int casualLeaveBalance;

   
    public Employee(String employeeId, String name, int sickLeaveBalance, int casualLeaveBalance) {
        this.employeeId = employeeId;
        this.name = name;
        this.sickLeaveBalance = sickLeaveBalance;
        this.casualLeaveBalance = casualLeaveBalance;
    }


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public int getSickLeaveBalance() {
        return sickLeaveBalance;
    }

    public void setSickLeaveBalance(int sickLeaveBalance) {
        this.sickLeaveBalance = sickLeaveBalance;
    }

    
    public int getCasualLeaveBalance() {
        return casualLeaveBalance;
    }

    public void setCasualLeaveBalance(int casualLeaveBalance) {
        this.casualLeaveBalance = casualLeaveBalance;
    }

    
    @Override
    public String toString() {
        return "Employee ID: " + employeeId + 
               ", Name: " + name + 
               ", Sick Leave Balance: " + sickLeaveBalance + 
               ", Casual Leave Balance: " + casualLeaveBalance;
    }
}

