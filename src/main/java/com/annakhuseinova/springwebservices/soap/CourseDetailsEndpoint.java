package com.annakhuseinova.springwebservices.soap;

import com.annakhuseinova.springwebservices.courses.*;
import com.annakhuseinova.springwebservices.exception.CourseNotFoundException;
import com.annakhuseinova.springwebservices.model.Course;
import com.annakhuseinova.springwebservices.model.ResponseStatus;
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
        if (course.isEmpty()){
            throw new CourseNotFoundException("Failed to find course with id: " + request.getId());
        }
        return mapCourseDetails(course);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request){
        List<Course> allCourses = service.findAll();
        return mapAllCourseDetails(allCourses);
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "DeleteCourseDetailsRequest")
    @ResponsePayload
    public DeleteCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request){
        ResponseStatus status  = service.deleteById(request.getId());
        DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
        response.setStatus(mapStatus(status));
        return response;
    }

    private Status mapStatus(ResponseStatus status) {
        if (status == ResponseStatus.FAILURE){
            return Status.FAILURE;
        }
        return Status.SUCCESS;
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
