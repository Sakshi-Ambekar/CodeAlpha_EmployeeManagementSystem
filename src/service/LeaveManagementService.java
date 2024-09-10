package service;

import model.Employee;
import model.LeaveRequest;
import repository.EmployeeRepository;
import repository.LeaveRequestRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class LeaveManagementService {
    private LeaveRequestRepository leaveRequestRepository;
    private EmployeeRepository employeeRepository;

    
    public LeaveManagementService(LeaveRequestRepository leaveRequestRepository, EmployeeRepository employeeRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
    }

    
    public String applyLeave(String employeeId, String leaveType, LocalDate startDate, LocalDate endDate) {
        Employee employee = employeeRepository.getEmployeeById(employeeId);
        if (employee == null) {
            return "Employee not found.";
        }

        
        long leaveDuration = ChronoUnit.DAYS.between(startDate, endDate) + 1;

        
        if ("Sick Leave".equalsIgnoreCase(leaveType)) {
            if (employee.getSickLeaveBalance() >= leaveDuration) {
                
                employee.setSickLeaveBalance(employee.getSickLeaveBalance() - (int) leaveDuration);
                leaveRequestRepository.addLeaveRequest(new LeaveRequest(0, employeeId, leaveType, startDate, endDate, "Pending"));
                return "Leave request applied successfully.";
            } else {
                return "Insufficient Sick Leave balance.";
            }
        } else if ("Casual Leave".equalsIgnoreCase(leaveType)) {
            if (employee.getCasualLeaveBalance() >= leaveDuration) {
               
                employee.setCasualLeaveBalance(employee.getCasualLeaveBalance() - (int) leaveDuration);
                leaveRequestRepository.addLeaveRequest(new LeaveRequest(0, employeeId, leaveType, startDate, endDate, "Pending"));
                return "Leave request applied successfully.";
            } else {
                return "Insufficient Casual Leave balance.";
            }
        } else {
            return "Invalid leave type.";
        }
    }

    
    public String approveLeave(int requestId) {
        LeaveRequest leaveRequest = leaveRequestRepository.getLeaveRequestById(requestId);
        if (leaveRequest != null && "Pending".equals(leaveRequest.getStatus())) {
            leaveRequest.setStatus("Approved");
            return "Leave request approved.";
        } else {
            return "Leave request not found or already processed.";
        }
    }

    
    public String rejectLeave(int requestId) {
        LeaveRequest leaveRequest = leaveRequestRepository.getLeaveRequestById(requestId);
        if (leaveRequest != null && "Pending".equals(leaveRequest.getStatus())) {
            leaveRequest.setStatus("Rejected");

            
            Employee employee = employeeRepository.getEmployeeById(leaveRequest.getEmployeeId());
            long leaveDuration = ChronoUnit.DAYS.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()) + 1;

            if ("Sick Leave".equalsIgnoreCase(leaveRequest.getLeaveType())) {
                employee.setSickLeaveBalance(employee.getSickLeaveBalance() + (int) leaveDuration);
            } else if ("Casual Leave".equalsIgnoreCase(leaveRequest.getLeaveType())) {
                employee.setCasualLeaveBalance(employee.getCasualLeaveBalance() + (int) leaveDuration);
            }

            return "Leave request rejected and balance restored.";
        } else {
            return "Leave request not found or already processed.";
        }
    }

    
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.getAllLeaveRequests();
    }
}


