package repository;

import model.LeaveRequest;

import java.util.ArrayList;
import java.util.List;

public class LeaveRequestRepository {
    private List<LeaveRequest> leaveRequests = new ArrayList<>();
    private int currentRequestId = 1;

    
    public void addLeaveRequest(LeaveRequest leaveRequest) {
        leaveRequest.setRequestId(currentRequestId++);
        leaveRequests.add(leaveRequest);
    }

    
    public LeaveRequest getLeaveRequestById(int requestId) {
        return leaveRequests.stream()
                .filter(request -> request.getRequestId() == requestId)
                .findFirst()
                .orElse(null);
    }

    
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequests;
    }

    
    public List<LeaveRequest> getLeaveRequestsByEmployeeId(String employeeId) {
        List<LeaveRequest> result = new ArrayList<>();
        for (LeaveRequest request : leaveRequests) {
            if (request.getEmployeeId().equals(employeeId)) {
                result.add(request);
            }
        }
        return result;
    }
}


