/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonarea.presentation;

import com.bonarea.dao.StudentDao;
import com.bonarea.dao.StudentDaoImpl;
import com.bonarea.model.Student;
import java.sql.SQLException;

/**
 *
 * @author alberto.casanova
 */
public class Principal {
   
    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        
        Student student = new Student();
        student.setFirstName("Alberto");
        student.setLastName("Casanova");
        student.setEmail("alberto.casanova@bonarea.com");
        
        StudentDao studentDao = new StudentDaoImpl();
        studentDao.addStudent(student);
        
        studentDao.getStudentById(student.getId());
        
        studentDao.getStudentsByAll();
        
        studentDao.deleteStudent(student.getId());
        
        studentDao.updateStudent("Higes",15);
        studentDao.getStudentById(15);
        
    }
    
}
