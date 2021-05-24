/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonarea.dao;

import com.bonarea.model.Student;
import java.sql.SQLException;

/**
 *
 * @author alberto.casanova
 */
public interface StudentDao {
    
    Student addStudent(Student student) throws SQLException, ClassNotFoundException;
    void getStudentById(Integer id);
    void getStudentsByAll();
    void updateStudent(String secondName, Integer id) throws SQLException, ClassNotFoundException;
    void deleteStudent(Integer id) throws SQLException, ClassNotFoundException;
    
}
