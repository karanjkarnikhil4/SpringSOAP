package com.nikhilkaranjkar.webservices.springsoap;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode=FaultCode.CUSTOM, 
customFaultCode="{http://nikhil.karanjkar/courses}001_COURSE_NOT_FOUND")
public class CourseNotFoundException extends RuntimeException {


/**
   * 
   */
  private static final long serialVersionUID = 4768628948001683591L;

public CourseNotFoundException(String message) {
	super(message);
}

}
