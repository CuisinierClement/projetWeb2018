/*
 * Creation : 8 juin 2016
 */
package com.inetpsa.pi201.manageprojects.rest.shared;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class DoNotRespectRulesException extends WebApplicationException {

    private static final long serialVersionUID = 587315978546L;

    public DoNotRespectRulesException() {
        super(Response.status(Response.Status.CONFLICT).build());
    }

    public DoNotRespectRulesException(String message) {
        super(Response.status(Response.Status.CONFLICT).entity(message).type(MediaType.TEXT_PLAIN).build());
    }

}
