/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Date;

/**
 *
 * @author user
 */
public class Employee {
    private int EmployeeID,DepartmentID;
    private String LastName,FirstName,Title,TitleOfCourtesy,Address;
    private Date BirthDate,HireDate;

    public Employee(int EmployeeID, int DepartmentID, String LastName, String FirstName, String Title, String TitleOfCourtesy, String Address, Date BirthDate, Date HireDate) {
        this.EmployeeID = EmployeeID;
        this.DepartmentID = DepartmentID;
        this.LastName = LastName;
        this.FirstName = FirstName;
        this.Title = Title;
        this.TitleOfCourtesy = TitleOfCourtesy;
        this.Address = Address;
        this.BirthDate = BirthDate;
        this.HireDate = HireDate;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public int getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(int DepartmentID) {
        this.DepartmentID = DepartmentID;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getTitleOfCourtesy() {
        return TitleOfCourtesy;
    }

    public void setTitleOfCourtesy(String TitleOfCourtesy) {
        this.TitleOfCourtesy = TitleOfCourtesy;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date BirthDate) {
        this.BirthDate = BirthDate;
    }

    public Date getHireDate() {
        return HireDate;
    }

    public void setHireDate(Date HireDate) {
        this.HireDate = HireDate;
    }

    @Override
    public String toString() {
        return "Employee{" + "EmployeeID=" + EmployeeID + ", DepartmentID=" + DepartmentID + ", LastName=" + LastName + ", FirstName=" + FirstName + ", Title=" + Title + ", TitleOfCourtesy=" + TitleOfCourtesy + ", Address=" + Address + ", BirthDate=" + BirthDate + ", HireDate=" + HireDate + '}';
    }
    
    


}


