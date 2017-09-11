/*
 * Creation : 15 avr. 2016
 */
package com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.seedstack.business.domain.BaseFactory;

public class ProjectFactoryDefault extends BaseFactory<Project> implements ProjectFactory {

    /**
     * {@inheritDoc}
     * 
     * @see com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.ProjectFactory#createProject(java.lang.String)
     */
    public Project createProject(String pProjectName) {

        if (pProjectName == null || "".equals(pProjectName)) {

            return null;
        }
        // create the project
        Project lProject = new Project();
        lProject.setname(pProjectName);

        List<Deliverable> lListDeliverable = new ArrayList<Deliverable>();
        lProject.setDeliverables(lListDeliverable);

        // missing the user

        return lProject;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.ProjectFactory#getDeliverable(java.lang.String,
     *      com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Project)
     */
    public Deliverable getDeliverable(String pNameDeliverable, Project pParentProject) {

        // Check if the Project or the Deliverable is null
        if (pParentProject == null || "".equals(pParentProject)) {
            return null;
        }
        if (pNameDeliverable == null || "".equals(pNameDeliverable)) {
            return null;
        }

        // get the list of Deliverables of Project
        List<Deliverable> lListDeliverable = pParentProject.getDeliverables();
        // check if the list of deliverables is null
        if (lListDeliverable == null || lListDeliverable.isEmpty()) {
            return null;
        }

        Deliverable lDeliverable = new Deliverable();
        lDeliverable.setname(pNameDeliverable);

        for (int i = 0; i < lListDeliverable.size(); i++) {
            if (pNameDeliverable.equals(lListDeliverable.get(i).getname())) {
                return lListDeliverable.get(i);
            }
        }
        // check if the given Deliverable exists in the list of Deliverables of the given Project
        return null;

    }

    /**
     * {@inheritDoc}
     * 
     * @see com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.ProjectFactory#addResponsibleToProject(com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.User,
     *      com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Project)
     */
    public void addResponsibleToProject(String pUserId, Project pParentProject) {

        if (pParentProject == null || pUserId == null) {
            // nothing to do in this case
        } else {

            // Check if the project responsible already exist
            if (pParentProject.getResponsable() == null || "".equals(pParentProject.getResponsable())) {

                pParentProject.setResponsable(pUserId);
            }
            // if the manager was already set to given project
            // update the manager of given project
            pParentProject.setResponsable(pUserId);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.ProjectFactory#addResponsibleToDeliverable(com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.User,
     *      com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Deliverable)
     */
    public void addResponsibleToDeliverable(String pUserId, Deliverable pDeliverable) {

        if (pDeliverable == null || pUserId == null) {
            // nothing to do in this case
        } else {

            if (pDeliverable.getResponsableId() == null || "".equals(pDeliverable.getResponsableId())) {

                // if the manager was already set to given Project
                pDeliverable.setResponsableId(pUserId);
            }

            // if the given Deliverable already has a manager
            // update the Responsible of that Deliverable
            pDeliverable.setResponsableId(pUserId);
        }

    }

    private Deliverable createDeliverable(String pDeliverableName, Date pDateStart, Date pDateEnd, Date pDateNewStart, Date pDateNewEnd,
            String pComment) {

        // the Deliverable name, the DateStart and DateEnd have to be insert
        if (pDeliverableName == null || "".equals(pDeliverableName)) {
            return null;
        }
        if (pDateStart == null) {
            return null;
        }
        if (pDateEnd == null) {
            return null;
        }

        Deliverable lDeliverable = new Deliverable();

        lDeliverable.setname(pDeliverableName);
        lDeliverable.setdateStart(pDateStart);
        lDeliverable.setdateEnd(pDateEnd);
        lDeliverable.setcomment(pComment);

        // if DateNewStart and DateNewEnd are not filled, insert values of dateStart and dateEnd respectively
        if (pDateNewStart != null) {
            lDeliverable.setdateNewStart(pDateNewStart);
        } else {
            lDeliverable.setdateNewStart(pDateStart);
        }

        if (pDateNewEnd != null) {
            lDeliverable.setdateNewEnd(pDateNewEnd);
        } else {
            lDeliverable.setdateNewEnd(pDateEnd);
        }

        return lDeliverable;

    }

    public Deliverable addDeliverableToProject(Project pParentProject, String pDeliverableName, Date pDateStart, Date pDateEnd, Date pDateNewStart,
            Date pDateNewEnd, String pComment) {

        if (pParentProject == null) {
            return null;
        }

        if (pDeliverableName == null || "".equals(pDeliverableName)) {
            return null;
        }

        // Check if the list of Deliverables exist in Project. If it isn't we create this list
        List<Deliverable> lListDeliverable = pParentProject.getDeliverables();

        if (lListDeliverable == null) {
            lListDeliverable = new ArrayList<Deliverable>();
            pParentProject.setDeliverables(lListDeliverable);
        }

        // Check if the deliverable already exists in the list of Deliverables

        for (int i = 0; i < lListDeliverable.size(); i++) {
            if (pDeliverableName.equals(lListDeliverable.get(i).getname())) {
                return null;
            }
        }

        Deliverable lDeliverable = this.createDeliverable(pDeliverableName, pDateStart, pDateEnd, pDateNewStart, pDateNewEnd, pComment);

        lListDeliverable.add(lDeliverable);

        return lDeliverable;

    }

}
