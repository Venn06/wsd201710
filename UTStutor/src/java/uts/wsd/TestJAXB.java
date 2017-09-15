	
package uts.wsd;
 
import java.util.*;
import java.io.*;
import javax.xml.bind.*;
 
public class TestJAXB implements Serializable {
 public static void main(String[] args) throws Exception {
  Students students = new Students();
  students.addStudent(new Student("ryan@ryanheise.com", "Ryan Heise", "blahblah","08/08/1998" ));
  students.addStudent(new Student("joe@bloggs.com", "Joe Bloggs", "passoword","01/01/2000"));
  Tutors tutors = new Tutors();
  tutors.addTutor(new Tutor("1@1.com", "first tutor", "password1", "01/01/1900", "WSD", "availble"));
  tutors.addTutor(new Tutor("2@2.com", "second tutor", "password2", "02/02/1900", "WSD", "availble"));
  // Boilerplate code to convert objects to XML...
  JAXBContext jc = JAXBContext.newInstance(Students.class);
  Marshaller m = jc.createMarshaller();
  m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
  m.marshal(students, System.out);
  
  JAXBContext jc2 = JAXBContext.newInstance(Tutors.class);
  Marshaller m2 = jc2.createMarshaller();
  m2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
  m2.marshal(tutors, System.out);
 }
 
 
}