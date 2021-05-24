/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonarea.dao;

import com.bonarea.model.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alberto.casanova
 */
public class StudentDaoImpl implements StudentDao {
    
    
    private final static String MYDRIVER = "com.mysql.cj.jdbc.Driver";
    
    private final static String CONNECTIONSTRING
        = "jdbc:mysql://eu-cdbr-west-01.cleardb.com/heroku_e4ab14e72e4b40b";
    private final static String USER = "bca8f14a114009";
    private final static String PWD = "e41ed60e";
    private final static String INSERT_STATEMENT = "INSERT INTO student(firstname, " +
        "lastname, email) values(?,?,?)";
    private final static String UPDATE_STATEMENT = "UPDATE student "  
                                                        + "SET lastname = ? "
                                                        + "WHERE id = ?";
    private final static String DELETE_STATEMENT = "DELETE FROM student WHERE id=?";
    private final static String GET_STATEMENT = "SELECT firstname, lastname, " + 
            "email FROM student WHERE id=?";
    private final static String GETALL_STATEMENT = "SELECT id, firstname, lastname, " + 
            "email FROM student ";

    @Override
    public Student addStudent(Student student) throws SQLException, ClassNotFoundException {
        Class.forName(MYDRIVER);
        Connection con = DriverManager.getConnection(CONNECTIONSTRING,USER, PWD);

        PreparedStatement preparedInsert = con.prepareStatement(INSERT_STATEMENT, PreparedStatement.RETURN_GENERATED_KEYS);
        
        preparedInsert.setString(1, student.getFirstName());
        preparedInsert.setString(2, student.getLastName());
        preparedInsert.setString(3, student.getEmail());
        preparedInsert.executeUpdate();
        
        // Se obtiene la clave generada
        ResultSet resultset = preparedInsert.getGeneratedKeys();
        while (resultset.next()) {
            int generatedKeyId = resultset.getInt(1);
            System.out.println("Clave generada = " + generatedKeyId);
            student.setId(generatedKeyId);
        }
        return student;
    }

    @Override
    //public Student getStudentById(Integer id) {
    public void getStudentById(Integer id) {
        
        try{
            Class.forName(MYDRIVER);
            Connection con = DriverManager.getConnection(CONNECTIONSTRING,USER, PWD);
            
            // create the java statement
            PreparedStatement preparedGet = con.prepareStatement(GET_STATEMENT);
            preparedGet.setInt(1, id);
            
            // execute the query, and get a java resultset
            ResultSet rs = preparedGet.executeQuery();

            // iterate through the java resultset
            while (rs.next()){
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String email = rs.getString("email");

                Student student = new Student();
                student.setId(id);
                student.setFirstName(firstName);
                student.setLastName(lastName);
                student.setEmail(email);
                
                System.err.println("El estudiante con ID: " + id + " se llama " +
                        student.getFirstName() + " " + student.getLastName() + 
                        " con email: " + student.getEmail());
            }
            preparedGet.close();
        }
        catch (Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        
    }
    
    @Override
    public void getStudentsByAll() {
        
        try{
            Class.forName(MYDRIVER);
            Connection con = DriverManager.getConnection(CONNECTIONSTRING,USER, PWD);
            
            // create the java statement
            PreparedStatement preparedGet = con.prepareStatement(GETALL_STATEMENT);
            
            // execute the query, and get a java resultset
            ResultSet rs = preparedGet.executeQuery();

            // iterate through the java resultset
            while (rs.next()){
                Integer id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String email = rs.getString("email");

                Student student = new Student();
                student.setId(id);
                student.setFirstName(firstName);
                student.setLastName(lastName);
                student.setEmail(email);
                
                System.err.println("El estudiante con ID: " + student.getId() + " se llama " +
                        student.getFirstName() + " " + student.getLastName() + 
                        " con email: " + student.getEmail());
            }
            preparedGet.close();
        }
        catch (Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        
    }
    
    @Override
    public void updateStudent(String secondName, Integer id) throws SQLException, ClassNotFoundException {
        
        try{
            Class.forName(MYDRIVER);
            Connection con = DriverManager.getConnection(CONNECTIONSTRING,USER, PWD);
            
            // create the java statement
            PreparedStatement preparedStatement = con.prepareStatement(UPDATE_STATEMENT);
            preparedStatement.setString(1, secondName);
            preparedStatement.setInt(2, id);
            
            // execute the query, and get a java resultset
            int affectedRecords  = preparedStatement.executeUpdate();

            // rows affected
            System.out.println("El ID " + id + " se ha actualizado con " + secondName + " filas afectadas: " + affectedRecords);

            preparedStatement.close();
        }
        catch (Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    
    @Override
    public void deleteStudent(Integer id) throws SQLException, ClassNotFoundException {
        
        try{
            Class.forName(MYDRIVER);
            Connection con = DriverManager.getConnection(CONNECTIONSTRING,USER, PWD);
            
            // create the java statement
            PreparedStatement preparedStatement = con.prepareStatement(DELETE_STATEMENT);
            preparedStatement.setInt(1, id);
            
            // execute the query, and get a java resultset
            int affectedRecords  = preparedStatement.executeUpdate();

            // rows affected
            System.out.println("El ID " + id + " se ha borrado, filas afectadas: " + affectedRecords);

            preparedStatement.close();
        }
        catch (Exception e){
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    
}
