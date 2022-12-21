package com.revature.pojos;


public class Ticket {

    private Integer ticketId;
    private Integer employeeId;
    private Integer managerId;
    private String description;
    private Double requestAmount;
    private Boolean pendingStatus;
    private Boolean approved;

    public Ticket() {
    }

    public Ticket(Integer ticketId, Integer employeeId, Integer managerId, String description, Double requestAmount, Boolean pendingStatus, Boolean approved) {
        this.ticketId = ticketId;
        this.employeeId = employeeId;
        this.managerId = managerId;
        this.description = description;
        this.requestAmount = requestAmount;
        this.pendingStatus = pendingStatus;
        this.approved = approved;
    }

    public Ticket(Integer employeeId, Integer managerId, String description, Double requestAmount, Boolean pendingStatus) {
        this.employeeId = employeeId;
        this.managerId = managerId;
        this.description = description;
        this.requestAmount = requestAmount;
        this.pendingStatus = pendingStatus;
    }

    public Ticket(Integer ticketId, Boolean approved) {
        this.ticketId = ticketId;
        this.approved = approved;
    }

    public Integer getTicketId() { return ticketId; }

    public void setTicketId(Integer ticketId) { this.ticketId = ticketId; }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(Double requestAmount) {
        this.requestAmount = requestAmount;
    }

    public Boolean getPendingStatus() {
        return pendingStatus;
    }

    public void setPendingStatus(Boolean pendingStatus) {
        this.pendingStatus = pendingStatus;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", employeeId=" + employeeId +
                ", managerId=" + managerId +
                ", description='" + description + '\'' +
                ", requestAmount=" + requestAmount +
                ", pendingStatus=" + pendingStatus +
                ", approved=" + approved +
                '}';
    }
}
