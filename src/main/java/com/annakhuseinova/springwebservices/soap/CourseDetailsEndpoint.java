package com.annakhuseinova.springwebservices.soap;

import com.annakhuseinova.springwebservices.courses.*;
import com.annakhuseinova.springwebservices.model.Course;
import com.annakhuseinova.springwebservices.service.CourseDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.Optional;

import static com.annakhuseinova.springwebservices.constants.Constants.NAMESPACE;

@Endpoint
@RequiredArgsConstructor
public class CourseDetailsEndpoint {

    private final CourseDetailsService service;

    @PayloadRoot(namespace = NAMESPACE, localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request){
        Optional<Course> course = service.findById(request.getId());
        return mapCourseDetails(course);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request){
        List<Course> allCourses = service.findAll();
        return mapAllCourseDetails(allCourses);
    }

    private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses){
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
        courses.forEach(course -> {
            CourseDetails courseDetails = mapCourseToCourseDetails(course);
            response.getCourseDetails().add(courseDetails);
        });
        return response;
    }

    private GetCourseDetailsResponse mapCourseDetails(Optional<Course> course){
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        response.setCourseDetails(mapCourseToCourseDetails(course.get()));
        return response;
    }

    private CourseDetails mapCourseToCourseDetails(Course course){
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }
}
