package com.nikhilkaranjkar.webservices.springsoap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import karanjkar.nikhil.courses.CourseDetailsType;
import karanjkar.nikhil.courses.DeleteCourseDetailRequest;
import karanjkar.nikhil.courses.DeleteCourseDetailResponse;
import karanjkar.nikhil.courses.GetCourseDetailsRequest;
import karanjkar.nikhil.courses.GetCourseDetailsResponse;

@Endpoint
public class CouseDetailsEndPoint {

  // method
  // input - GetCourseDetailsRequest
  // output - GetCourseDetailsResponse

  @Autowired private CourseDetailsService courseDetailsService;

  @PayloadRoot(namespace = "http://nikhil.karanjkar/courses", localPart = "GetCourseDetailsRequest")
  @ResponsePayload
  public GetCourseDetailsResponse processCourseDetailsRequest(
      @RequestPayload GetCourseDetailsRequest request) {

    Course course = courseDetailsService.findById(request.getId());
    
    if(course == null)
    throw new CourseNotFoundException("The course you searched for does not exist");

    CourseDetailsType courseDetailsType = getCourseDetails(course);
    GetCourseDetailsResponse courseDetailsResponse = new GetCourseDetailsResponse();
    courseDetailsResponse.getCourseDetails().add(courseDetailsType);
    return courseDetailsResponse;
  }

  @PayloadRoot(
      namespace = "http://nikhil.karanjkar/courses",
      localPart = "GetAllCourseDetailsRequest")
  @ResponsePayload
  public GetCourseDetailsResponse getAllCourseDetails() {

    List<Course> courses = courseDetailsService.getAllCourseDetails();
    GetCourseDetailsResponse courseDetailsResponse = new GetCourseDetailsResponse();

    for (Course course : courses) {

      CourseDetailsType courseDetailsType = getCourseDetails(course);
      courseDetailsResponse.getCourseDetails().add(courseDetailsType);
    }

    return courseDetailsResponse;
  }

    @PayloadRoot(namespace = "http://nikhil.karanjkar/courses", localPart = "DeleteCourseDetailRequest")
    @ResponsePayload
    public DeleteCourseDetailResponse  deleteCourseDetail(
        @RequestPayload DeleteCourseDetailRequest request) {
  
      int status = courseDetailsService.deleteById(request.getId());
  
      DeleteCourseDetailResponse courseDetailsResponse = new DeleteCourseDetailResponse();
      courseDetailsResponse.setStatus(status);
      return courseDetailsResponse;
    }
    

  private CourseDetailsType getCourseDetails(Course course) { // TODO Auto-generated method stub
    CourseDetailsType courseDetailsType = new CourseDetailsType();
    courseDetailsType.setId(course.getId());
    courseDetailsType.setName(course.getName());
    courseDetailsType.setDescription(course.getDescription());
    return courseDetailsType;
  }
}
