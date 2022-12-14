package com.revature.persistence;

import com.revature.pojos.Ticket;

import java.sql.*;

public class TicketDao {
    // DAOs are the objects where we implement the CRUD behavior
    //in order to invoke various CRUD operations in the persistence layer, we need to start the behavior,
    //the flow of execution, in the web API layer, access a service layer class, and finally access the DAO.

    private Connection connection;

    public TicketDao(){
        this.connection = ConnectionManager.getConnection();
    }

    public void create(Ticket ticket) {
        try {
            String sql = "INSERT INTO tickets (ticketId, employeeId, managerId, description, requestAmount, pendingStatus,approved) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, ticket.getTicketId());
            pstmt.setInt(2, ticket.getManagerId());
            pstmt.setInt(3, ticket.getEmployeeId());
            pstmt.setString(4, ticket.getDescription());
            pstmt.setBoolean(5, ticket.getPendingStatus());
            pstmt.setBoolean(6, ticket.getApproved());

            pstmt.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Ticket read(Integer id) {
        Ticket ticket = new Ticket();

        try {
            String sql = "SELECT * FROM ticket WHERE ticket_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                ticket.setTicketId(rs.getInt("ticket_id"));
                ticket.setEmployeeId(rs.getInt("employee_id"));
                ticket.setManagerId(rs.getInt("manager_id"));
                ticket.setDescription(rs.getString("description"));
                ticket.setPendingStatus(rs.getBoolean("pending_status"));
                ticket.setApproved(rs.getBoolean("approved"));
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return ticket;
    }

    public void update(Ticket ticket) {
        try {
            String sql = "UPDATE tasks SET pending_status = ?, approved = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setBoolean(1, ticket.getPendingStatus());
            pstmt.setBoolean(2, ticket.getApproved());

            pstmt.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try {
            String sql = "DELETE FROM tickets WHERE task_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();


        } catch(SQLException e) {
            e.printStackTrace();
        }
    }





}
