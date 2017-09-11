/*
 * Creation : 9 mai 2016
 */
package com.inetpsa.pi201.manageprojects.rest.user;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang.StringUtils;
import org.javatuples.Pair;
import org.seedstack.business.finder.Range;
import org.seedstack.business.finder.Result;
import org.seedstack.business.view.PaginatedView;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.User;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.UserFactory;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.UserRepository;
import com.inetpsa.pi201.manageprojects.rest.shared.AlreadyExistException;
import com.inetpsa.pi201.manageprojects.rest.shared.DoNotRespectRulesException;
import com.inetpsa.pi201.manageprojects.rest.shared.MandatoryDataNotFillIn;

@Path("/users")
@Transactional
@JpaUnit("manage-domain")
public class UserResource {

    @Inject
    private UserFinder mUserFinder;

    @Inject
    private UserAssembleur mUserAssembleur;

    @Inject
    private UserRepository mUserRepository;

    @Inject
    private UserFactory mUserFactory;

    @Context
    private UriInfo uriInfo;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response users(@QueryParam("searchString") String searchString, @QueryParam("pageIndex") Integer pageIndex,
            @QueryParam("pageSize") Integer pageSize) {
        Map<String, Object> criteria = new HashMap<String, Object>();

        // criteria map is used to build where clause
        // p.login, p.name, p.summary, p.firstName

        String likeOperator = "like";
        String likeValue = "'%" + searchString + "%'";
        Pair<String, String> operatorValueCondition = new Pair<String, String>(likeOperator, likeValue);

        if (StringUtils.isNotEmpty(searchString)) {
            criteria.put("login", operatorValueCondition);
            criteria.put("name", operatorValueCondition);
            criteria.put("firstName", operatorValueCondition);
        }
        if (pageIndex != null && pageSize != null) {
            Result<UserRepresentation> result = mUserFinder.findAllUser(Range.rangeFromPageInfo(pageIndex, pageSize), criteria);
            LOGGER.info("RESULT : " + result + "; PAGESIZE : " + pageSize + ";PAGEINDEX : " + pageIndex);
            if (result == null || result.getSize() == 0) {
                return Response.ok().build();
            }
            PaginatedView<UserRepresentation> paginatedView = new PaginatedView<UserRepresentation>(result, pageSize, pageIndex);

            return Response.ok(paginatedView).build();
        }

        return Response.ok(mUserFinder.findAllUser()).build();
    }

    /**
     * Gets a user by id.
     * 
     * @param p_userId the user id
     * @return a user representation or 404 if not found
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userId}")
    public Response users(@PathParam("userId") String pUserId) {

        UserRepresentation lUserRepresentation = mUserFinder.findUserbyId(pUserId);
        if (lUserRepresentation == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(lUserRepresentation).build();
    }

    /**
     * Updates a User.
     * 
     * @param UserRepresentation the user representation
     * @param userId the customer id
     * @return the updated user (200)
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{userId}")
    public Response updateUser(UserRepresentation pUserRepresentation, @PathParam("userId") String pUserId) {
        // verify that the user respects rules of creation
        assertUserRespectRules(pUserRepresentation);

        if (!pUserId.equals(pUserRepresentation.getLogin())) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN_TYPE).entity("User cannot be updated").build();
        }

        // Frank : TO see
        // try {
        // l_user = m_userRepository.load(p_userId);
        //
        // customer = fluentAssembler.merge(UserRepresentation).into(Customer.class).fromRepository().orFail();
        //
        // } catch (AggregateNotFoundException e) {
        // return Response.status(Response.Status.NOT_FOUND).build();
        // }

        // transform first Name, and Name to upper case
        pUserRepresentation.setFirstName(pUserRepresentation.getFirstName().toUpperCase());
        pUserRepresentation.setName(pUserRepresentation.getName().toUpperCase());

        User lUser = mUserRepository.load(pUserId);
        mUserAssembleur.mergeAggregateWithDto(lUser, pUserRepresentation);
        mUserRepository.persist(lUser);

        UserRepresentation lUserRepresentation1 = mUserAssembleur.assembleDtoFromAggregate(lUser);

        return Response.created(URI.create(this.uriInfo.getRequestUri().toString() + "/" + lUserRepresentation1.getLogin()))
                .entity(lUserRepresentation1).build();
    }

    /**
     * Adds a User.
     * 
     * @param p_UserRepresentation the customer representation
     * @return the new customer (201)
     * @throws URISyntaxException if an URI error occurs
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserRepresentation pUserRepresentation) throws URISyntaxException {

        // verify that the user respects rules of creation
        assertUserRespectRules(pUserRepresentation);

        // we verify that the user doesn't already exist
        User lUser = mUserRepository.load(pUserRepresentation.getLogin());

        // transform login, first Name, and Name to upper case
        pUserRepresentation.setLogin(pUserRepresentation.getLogin().toUpperCase());
        pUserRepresentation.setFirstName(pUserRepresentation.getFirstName().toUpperCase());
        pUserRepresentation.setName(pUserRepresentation.getName().toUpperCase());
        // verify that the user doesn't already exist
        assertUserDoNotAlreadyExists(pUserRepresentation);

        lUser = mUserFactory.createUser(pUserRepresentation.getLogin(), pUserRepresentation.getName(), pUserRepresentation.getFirstName());

        mUserAssembleur.mergeAggregateWithDto(lUser, pUserRepresentation);
        mUserRepository.persist(lUser);

        UserRepresentation lUserRepresentation1 = mUserAssembleur.assembleDtoFromAggregate(lUser);

        return Response.created(URI.create(this.uriInfo.getRequestUri().toString() + "/" + lUserRepresentation1.getLogin()))
                .entity(lUserRepresentation1).build();

    }

    private void assertUserDoNotAlreadyExists(UserRepresentation pUserRepresentation) {
        User lUser = mUserRepository.load(pUserRepresentation.getLogin());
        if (lUser != null) {
            throw new AlreadyExistException("user already exists");
        }
    }

    private void assertUserRespectRules(UserRepresentation pUserRepresentation) {

        if (pUserRepresentation.getLogin() == null || pUserRepresentation.getFirstName() == null || pUserRepresentation.getName() == null) {
            throw new MandatoryDataNotFillIn("mandatory data not fill in");
        }

        // the login has to be composed of one letter and 6 numbers (next to the letter)
        int length = pUserRepresentation.getLogin().length();
        // get the first character of the login
        // // get the substring composed of 6 numbers of the login
        // create User only if the l_user is empty and if the first letter of the login is a capital letter
        if (length != 7 || !Character.isLetter(pUserRepresentation.getLogin().charAt(0))
                || !StringUtils.isNumeric(pUserRepresentation.getLogin().substring(1, 6))) {
            throw new DoNotRespectRulesException("user doesn't respect rules");
        }
    }

    /**
     * Deletes a User.
     * 
     * @param p_userId the user id
     * @return ok(200) or 404 if the user did exist
     */
    @DELETE
    @Path("/{userId}")
    public Response deleteUser(@PathParam("userId") String pUserId) {
        User lUser = mUserRepository.load(pUserId);

        if (lUser != null) {
            mUserRepository.delete(lUser);
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.status(Status.OK).build();
    }

}
