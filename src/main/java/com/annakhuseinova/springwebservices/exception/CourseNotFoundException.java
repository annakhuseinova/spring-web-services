package com.annakhuseinova.springwebservices.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

/**
 * It is also possible to annotate exception classes with the @SoapFault annotation, to indicate the
 * SOAP Fault that should be returned whenever that exception is thrown. In order for these annotations
 * to be picked up, you need to add the SoapFaultAnnotationExceptionResolver to your application
 * context. The elements of the annotation include a fault code enumeration, fault string or reason, and
 * language.
 * */


@SoapFault(faultCode = FaultCode.CLIENT)
public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String message) {
        super(message);
    }
}
