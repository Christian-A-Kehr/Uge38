/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Christian
 */
public class StudentInfo {

    public String fullName;
    public long studentId;
    public String classNameThisSemester;
    public String classDescription;

    public StudentInfo(String firstName, String Lastname, String classNameThisSemester, String classDescription) {
        this.fullName = firstName + " " + Lastname;
        this.classNameThisSemester = classNameThisSemester;
        this.classDescription = classDescription;
    }
    
    // Afslut lørdag. 
    List<StudentInfo> getStudentInfo(){
        List<StudentInfo> List = new ArrayList<StudentInfo>();
        return List;
    }


    
    
}
