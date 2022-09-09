package com.jGuru.PatientDetails.exceptions;

import com.jGuru.PatientDetails.entity.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.security.Principal;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class PatientEntityExceptionHandlerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void patientNotFoundException() {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage("Error Message");
        errorMessage.setStatus(HttpStatus.NOT_FOUND);
        PatientEntityExceptionHandler patientEntityExceptionHandler = new PatientEntityExceptionHandler();
        patientEntityExceptionHandler.patientNotFoundException(new PatientDataNotFound(errorMessage.getMessage()), new WebRequest() {
            @Override
            public String getHeader(String headerName) {
                return null;
            }

            @Override
            public String[] getHeaderValues(String headerName) {
                return new String[0];
            }

            @Override
            public Iterator<String> getHeaderNames() {
                return null;
            }

            @Override
            public String getParameter(String paramName) {
                return null;
            }

            @Override
            public String[] getParameterValues(String paramName) {
                return new String[0];
            }

            @Override
            public Iterator<String> getParameterNames() {
                return null;
            }

            @Override
            public Map<String, String[]> getParameterMap() {
                return null;
            }

            @Override
            public Locale getLocale() {
                return null;
            }

            @Override
            public String getContextPath() {
                return null;
            }

            @Override
            public String getRemoteUser() {
                return null;
            }

            @Override
            public Principal getUserPrincipal() {
                return null;
            }

            @Override
            public boolean isUserInRole(String role) {
                return false;
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public boolean checkNotModified(long lastModifiedTimestamp) {
                return false;
            }

            @Override
            public boolean checkNotModified(String etag) {
                return false;
            }

            @Override
            public boolean checkNotModified(String etag, long lastModifiedTimestamp) {
                return false;
            }

            @Override
            public String getDescription(boolean includeClientInfo) {
                return null;
            }

            @Override
            public Object getAttribute(String name, int scope) {
                return null;
            }

            @Override
            public void setAttribute(String name, Object value, int scope) {

            }

            @Override
            public void removeAttribute(String name, int scope) {

            }

            @Override
            public String[] getAttributeNames(int scope) {
                return new String[0];
            }

            @Override
            public void registerDestructionCallback(String name, Runnable callback, int scope) {

            }

            @Override
            public Object resolveReference(String key) {
                return null;
            }

            @Override
            public String getSessionId() {
                errorMessage.getStatus();
                return null;
            }

            @Override
            public Object getSessionMutex() {
                return null;
            }
        });

    }
}