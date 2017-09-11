package com.inetpsa.pi201.manageprojects.infrastructure.finders.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Project;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.ProjectRepository;
import com.inetpsa.pi201.manageprojects.rest.project.ProjectAssembler;
import com.inetpsa.pi201.manageprojects.rest.project.ProjectFinder;
import com.inetpsa.pi201.manageprojects.rest.project.ProjectRepresentation;

public class ProjectFinderJpa implements ProjectFinder {

    @Inject
    private ProjectRepository mProjectRepository;

    @Inject
    private ProjectAssembler mProjectAssembler;

    @Override
    public ProjectRepresentation findProjectById(String pProjectId) {
        Project lProject = mProjectRepository.load(pProjectId);

        if (lProject == null) {
            return null;
        }

        ProjectRepresentation lProjectRepresentation = new ProjectRepresentation();
        mProjectAssembler.assembleDtoFromAggregate(lProjectRepresentation, lProject);
        ;
        return lProjectRepresentation;
    }

    @Override
    public List<ProjectRepresentation> findAllProjects() {
        List<Project> lListProjects = mProjectRepository.loadAll();
        List<ProjectRepresentation> lListProjectsRepresentations = new ArrayList<ProjectRepresentation>();
        for (Project project : lListProjects) {
            ProjectRepresentation lProjectRepresentation = new ProjectRepresentation();
            mProjectAssembler.assembleDtoFromAggregate(lProjectRepresentation, project);
            lProjectRepresentation.setname(lProjectRepresentation.getname().toUpperCase());
            lListProjectsRepresentations.add(lProjectRepresentation);
        }

        return lListProjectsRepresentations;

    }

}
