/*
 * Creation : 8 juin 2016
 */
package com.inetpsa.pi201.manageprojects.rest.shared;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AlreadyExistException extends WebApplicationException {

    private static final long serialVersionUID = 354054054054L;

    public AlreadyExistException() {
        super(Response.status(Response.Status.CONFLICT).build());
    }

    public AlreadyExistException(String message) {
        super(Response.status(Response.Status.CONFLICT).entity(message).type(MediaType.TEXT_PLAIN).build());
    }

}
