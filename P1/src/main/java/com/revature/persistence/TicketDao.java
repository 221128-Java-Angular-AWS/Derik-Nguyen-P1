package com.revature.persistence;

import com.revature.pojos.Ticket;


import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class TicketDao {
    // DAOs are the objects where we implement the CRUD behavior
    //in order to invoke various CRUD operations in the persistence layer, we need to start the behavior,
    //the flow of execution, in the web API layer, access a servlet layer class,
    // creating a POJO or model in the servlet layer class
    // access a service layer class, and finally access the DAO
    // passing our POJOs through the layers


    private Connection connection;

    public TicketDao(){
        this.connection = ConnectionManager.getConnection();
    }

    public void create(Ticket ticket) {
        try {
            String sql = "INSERT INTO tickets (employee_id, manager_id, description, request_amount, pending_status) VALUES (?,?,?,?,TRUE);";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, ticket.getEmployeeId());
            pstmt.setInt(2, ticket.getManagerId());
            pstmt.setString(3, ticket.getDescription());
            pstmt.setDouble(4, ticket.getRequestAmount());
            //pstmt.setBoolean(5, ticket.getPendingStatus());

            pstmt.executeUpdate();



        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Ticket read(Integer ticket_id) {
        Ticket ticket = new Ticket();

        try {
            String sql = "SELECT * FROM tickets WHERE ticket_id = ?;";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, ticket_id);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                ticket.setTicketId(rs.getInt("ticket_id"));
                ticket.setEmployeeId(rs.getInt("employee_id"));
                ticket.setManagerId(rs.getInt("manager_id"));
                ticket.setDescription(rs.getString("description"));
                ticket.setRequestAmount(rs.getDouble("request_amount"));
                ticket.setPendingStatus(rs.getBoolean("pending_status"));
                ticket.setApproved(rs.getBoolean("approved"));
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return ticket;
    }

    public Set<Ticket> getAllTickets(){
        String sql = "SELECT * FROM tickets WHERE pending_status = TRUE";
        Set<Ticket> setTickets = new HashSet<>();

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                Ticket ticket = new Ticket(rs.getInt("ticket_id"),
                        rs.getInt("employee_id"),
                        rs.getInt("manager_id"),
                        rs.getString("description"),
                        rs.getDouble("request_amount"),
                        rs.getBoolean("pending_status"),
                        rs.getBoolean("approved"));
                        setTickets.add(ticket);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return setTickets;
    }

    public void update(Ticket ticket) {


        try {
            String sql = "UPDATE tickets SET approved = ?, pending_status = DEFAULT WHERE ticket_id = ?;";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(2, ticket.getTicketId());
            pstmt.setBoolean(1, ticket.getApproved());




            pstmt.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(Integer ticket_id) {
        try {
            String sql = "DELETE FROM tickets WHERE ticket_id = ?;";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, ticket_id);
            pstmt.executeUpdate();


        } catch(SQLException e) {
            e.printStackTrace();
        }
    }





}
