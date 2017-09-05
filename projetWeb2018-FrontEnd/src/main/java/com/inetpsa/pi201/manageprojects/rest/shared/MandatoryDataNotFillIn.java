/*
 * Creation : 9 juin 2016
 */
package com.inetpsa.pi201.manageprojects.rest.shared;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MandatoryDataNotFillIn extends WebApplicationException {

    private static final long serialVersionUID = 851493357711L;

    public MandatoryDataNotFillIn() {
        super(Response.status(Response.Status.CONFLICT).build());
    }

    public MandatoryDataNotFillIn(String message) {
        super(Response.status(Response.Status.CONFLICT).entity(message).type(MediaType.TEXT_PLAIN).build());
    }
}
