/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.rest;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBException;
import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import uts.wsd.*;
/**
 *
 * @author Vennwen
 */
public class UTStutorService {
    @Context
    private ServletContext application;
    
    private DiaryApplication getApp(){
     // The web server can handle requests from different clients in parallel.
     // These are called "threads".
     //
     // We do NOT want other threads to manipulate the application object at the same
     // time that we are manipulating it, otherwise bad things could happen.
     //
     // The "synchronized" keyword is used to lock the application object while
     // we're manpulating it.
     synchronized (application) {
      DiaryApplication App;
      App = (DiaryApplication)application.getAttribute("App");
      if (App == null) {
       App = new DiaryApplication();
       
      }
      return App;
     }
    
    }
    
}
