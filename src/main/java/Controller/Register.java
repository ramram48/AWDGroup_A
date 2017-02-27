/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bus.BusinessExceptions;

import java.io.Serializable;
import Entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author a
 */
@ManagedBean(name = "register")
@RequestScoped
public class Register implements Serializable {

    private PersonStatus personstatus = new PersonStatus();
    private List<PersonStatus> personstatuses = new ArrayList<PersonStatus>();
    Addres addres = new Addres();
    private List<Addres> adreslist = new ArrayList<Addres>();
    Person person = new Person();
    Mail Maddres = new Mail();
    private List<Mail> MailList = new ArrayList<Mail>();
    Student student = new Student();
    private List<Student> studentlist = new ArrayList<Student>();
    Staff staff = new Staff();
    String statusname;

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }
    public List<Addres> getAdreslist() {
        return adreslist;
    }

    public void setAdreslist(List<Addres> adreslist) {
        this.adreslist = adreslist;
    }

    public Mail getMaddres() {
        return Maddres;
    }

    public void setMaddres(Mail Maddres) {
        this.Maddres = Maddres;
    }

    public List<Mail> getMailList() {
        return MailList;
    }

    public void setMailList(List<Mail> MailList) {
        this.MailList = MailList;
    }

    public List<Student> getStudentlist() {
        return studentlist;
    }

    public void setStudentlist(List<Student> studentlist) {
        this.studentlist = studentlist;
    }

    public Addres getAddres() {
        return addres;
    }

    public void setAddres(Addres addres) {
        this.addres = addres;
    }

    public PersonStatus getPersonstatus() {
        return personstatus;
    }

    public void setPersonstatus(PersonStatus personstatus) {
        this.personstatus = personstatus;
    }

    public List<PersonStatus> getPersonstatuses() {
        return personstatuses;
    }

    public void setPersonstatuses(List<PersonStatus> personstatuses) {
        this.personstatuses = personstatuses;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    @EJB
    private Bus.PersonBussines pb;

    public void addpersonstatus() throws BusinessExceptions {

        pb.addpersonstatus(getPersonstatus());
        setPersonstatuses(pb.getpersonstatus());
        Maddres.getNumber2();
    }

    public void delete(PersonStatus ps) {

    }

    public void Add() {

    }

    public void Edit(PersonStatus ps) throws BusinessExceptions {

        ps.setEditable(true);
        pb.updatePersonStatus(ps);
        setPersonstatuses(pb.getpersonstatus());

    }

    public void CancelEdit(PersonStatus ps) throws BusinessExceptions {
        ps.setEditable(false);
        pb.updatePersonStatus(ps);
        setPersonstatuses(pb.getpersonstatus());

    }

    public void Save(PersonStatus ps) throws BusinessExceptions {
        //
System.out.println(getStatusname());
        ps.setEditable(false);
        //ps.setStatusName();
        
        pb.updatePersonStatus(ps);
        
        setPersonstatuses(pb.getpersonstatus());

    }

    public void AddPerson() throws BusinessExceptions {

        if (person.getPersonStatus().getId() == 201) {
            MailList.add(getMaddres());
            person.setPersonStatus(personstatuses.get(0));
            person.setMails(MailList);
            adreslist.add(getAddres());
            person.setAddreses(adreslist);
            student.setPerson(getPerson());
            pb.add1(getStudent());
        }
    }

    /**
     * Creates a new instance of Register
     *
     * @throws Bus.BusinessExceptions
     */
    @PostConstruct
    public void onPageLoad() {
        try {
            // Do something
            setPersonstatuses(pb.getpersonstatus());
        } catch (BusinessExceptions ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Register() throws BusinessExceptions {

    }

}
