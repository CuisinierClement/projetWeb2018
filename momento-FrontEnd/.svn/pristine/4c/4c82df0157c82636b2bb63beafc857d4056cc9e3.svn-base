/**Creation:29 juin 2016*/
package com.inetpsa.pi201.manageprojects.rest.project;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Project;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.ProjectFactory;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.ProjectRepository;
import com.inetpsa.pi201.manageprojects.rest.shared.AlreadyExistException;
import com.inetpsa.pi201.manageprojects.rest.shared.MandatoryDataNotFillIn;

@Path("/projects")
@Transactional
@JpaUnit("manage-domain")
public class ProjectResource {

    @Inject
    private ProjectFinder mProjectFinder;

    @Inject
    private ProjectAssembler mProjectAssembler;

    @Inject
    private ProjectRepository mProjectRepository;

    @Inject
    private ProjectFactory mProjectFactory;

    @Context
    private UriInfo uriInfo;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response projects() {
        LOGGER.info("RESULT Project : ");
        List<ProjectRepresentation> lProjectList = mProjectFinder.findAllProjects();
        if (lProjectList != null) {
            return Response.ok(lProjectList).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }

    }

    /**
     * Gets a user by id.
     * 
     * @param p_userId the user id
     * @return a user representation or 404 if not found
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{projectId}")
    public Response project(@PathParam("projectId") String pProjectId) {
        LOGGER.info("RESULT : ");
        ProjectRepresentation lProjectRepresentation = mProjectFinder.findProjectById(pProjectId);
        if (lProjectRepresentation == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(lProjectRepresentation).build();
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
    @Path("/{projectId}")
    public Response updateProject(ProjectRepresentation pProjectRepresentation, @PathParam("projectId") String pProjectId) {
        // verify that the user respects rules of creation
        assertProjectRespectRules(pProjectRepresentation);

        if (!pProjectId.equals(pProjectRepresentation.getname())) {
            return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN_TYPE).entity("Project cannot be updated").build();
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

        // transform Name to upper case
        // pProjectRepresentation.setname(pProjectRepresentation.getname().toUpperCase());

        Project lProject = mProjectRepository.load(pProjectId);
        mProjectAssembler.mergeAggregateWithDto(lProject, pProjectRepresentation);
        mProjectRepository.persist(lProject);

        ProjectRepresentation lProjectRepresentation1 = mProjectAssembler.assembleDtoFromAggregate(lProject);

        lProjectRepresentation1.setname(lProjectRepresentation1.getname().replaceAll("\\s", ""));
        return Response.created(URI.create(this.uriInfo.getRequestUri().toString() + "/" + lProjectRepresentation1.getname()))
                .entity(lProjectRepresentation1).build();

    }

    /**
     * Adds a Project.
     * 
     * @param p_ProjectRepresentation the project representation
     * @return the new project (201)
     * @throws URISyntaxException if an URI error occurs
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProject(ProjectRepresentation pProjectRepresentation) throws URISyntaxException {

        // verify that the project respects rules of creation
        assertProjectRespectRules(pProjectRepresentation);

        // we verify that the project doesn't already exist
        Project lProject = mProjectRepository.load(pProjectRepresentation.getname());

        // ProjectRepresentation pProjectRepresentation2 = new ProjectRepresentation();
        // transform name to upper case
        pProjectRepresentation.setname(pProjectRepresentation.getname().toUpperCase());
        // verify that the project doesn't already exist
        assertProjectDoNotAlreadyExists(pProjectRepresentation);

        lProject = mProjectFactory.createProject(pProjectRepresentation.getname());
        String lResponsableId = pProjectRepresentation.getResponsable();
        mProjectFactory.addResponsibleToProject(lResponsableId, lProject);

        mProjectAssembler.mergeAggregateWithDto(lProject, pProjectRepresentation);
        mProjectRepository.persist(lProject);

        ProjectRepresentation lProjectRepresentation1 = mProjectAssembler.assembleDtoFromAggregate(lProject);

        lProjectRepresentation1.setname(lProjectRepresentation1.getname().replaceAll("\\s", ""));
        return Response.created(URI.create(this.uriInfo.getRequestUri().toString() + "/" + lProjectRepresentation1.getname()))
                .entity(lProjectRepresentation1).build();

    }

    private void assertProjectDoNotAlreadyExists(ProjectRepresentation pProjectRepresentation) {
        Project lProject = mProjectRepository.load(pProjectRepresentation.getname());
        if (lProject != null) {
            throw new AlreadyExistException("project already exists");
        }
    }

    private void assertProjectRespectRules(ProjectRepresentation pProjectRepresentation) {

        if (pProjectRepresentation.getname() == null || pProjectRepresentation.getResponsable() == null) {
            throw new MandatoryDataNotFillIn("mandatory data not fill in");
        }
    }

    /**
     * Deletes a Project.
     * 
     * @param p_projectId the project id
     * @return ok(200) or 404 if the project did exist
     */
    @DELETE
    @Path("/{projectId}")
    public Response deleteProject(@PathParam("projectId") String pProjectId) {
        Project lProject = mProjectRepository.load(pProjectId);

        if (lProject != null) {
            mProjectRepository.delete(lProject);
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.status(Status.OK).build();
    }

}
