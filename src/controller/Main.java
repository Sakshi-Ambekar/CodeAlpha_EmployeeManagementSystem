package controller;
import model.Employee;
import model.LeaveRequest;
import repository.EmployeeRepository;
import repository.LeaveRequestRepository;
import service.LeaveManagementService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        EmployeeRepository employeeRepository = new EmployeeRepository();
        LeaveRequestRepository leaveRequestRepository = new LeaveRequestRepository();
        LeaveManagementService leaveManagementService = new LeaveManagementService(leaveRequestRepository, employeeRepository);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Employee Leave Management System =====");
            System.out.println("1. Apply for Leave");
            System.out.println("2. Approve Leave");
            System.out.println("3. Reject Leave");
            System.out.println("4. View All Leave Requests");
            System.out.println("5. View Employee Leave Balances");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  

            switch (option) {
                case 1:
                    
                    System.out.print("Enter Employee ID: ");
                    String employeeId = scanner.nextLine();
                    System.out.print("Enter Leave Type (Sick Leave/Casual Leave): ");
                    String leaveType = scanner.nextLine();
                    System.out.print("Enter Start Date (yyyy-mm-dd): ");
                    LocalDate startDate = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter End Date (yyyy-mm-dd): ");
                    LocalDate endDate = LocalDate.parse(scanner.nextLine());

                    String result = leaveManagementService.applyLeave(employeeId, leaveType, startDate, endDate);
                    System.out.println(result);
                    break;

                case 2:
                    
                    System.out.print("Enter Leave Request ID to Approve: ");
                    int requestIdToApprove = scanner.nextInt();
                    scanner.nextLine();  
                    String approvalResult = leaveManagementService.approveLeave(requestIdToApprove);
                    System.out.println(approvalResult);
                    break;

                case 3:
                    
                    System.out.print("Enter Leave Request ID to Reject: ");
                    int requestIdToReject = scanner.nextInt();
                    scanner.nextLine();  
                    String rejectionResult = leaveManagementService.rejectLeave(requestIdToReject);
                    System.out.println(rejectionResult);
                    break;

                case 4:
                    
                    List<LeaveRequest> leaveRequests = leaveManagementService.getAllLeaveRequests();
                    System.out.println("\n--- All Leave Requests ---");
                    for (LeaveRequest request : leaveRequests) {
                        System.out.println(request);
                    }
                    break;

                case 5:
                    
                    System.out.print("Enter Employee ID: ");
                    String empId = scanner.nextLine();
                    Employee employee = employeeRepository.getEmployeeById(empId);
                    if (employee != null) {
                        System.out.println(employee);
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 6:
                    
                    System.out.println("Exiting the system. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
