package com.annakhuseinova.springwebservices.soap;

import com.annakhuseinova.springwebservices.courses.CourseDetails;
import com.annakhuseinova.springwebservices.courses.GetCourseDetailsRequest;
import com.annakhuseinova.springwebservices.courses.GetCourseDetailsResponse;
import com.annakhuseinova.springwebservices.service.CourseDetailsService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import static com.annakhuseinova.springwebservices.constants.Constants.NAMESPACE;

@Endpoint
public class CourseDetailsEndpoint {

    private CourseDetailsService service;


    @PayloadRoot(namespace = NAMESPACE, localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request){
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(request.getId());
        courseDetails.setName("Microservices Course");
        courseDetails.setDescription("That would be a wonderful course!");
        response.setCourseDetails(courseDetails);
        return response;
    }
}
