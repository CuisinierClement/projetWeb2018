package com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project;

import java.util.List;

import org.seedstack.business.domain.GenericRepository;

public interface ProjectRepository extends GenericRepository<Project, String> {

    /**
     * @param p_Projects
     */
    public void persistList(List<Project> pProjects);

    /**
     * Load All projects
     *
     * @return the list of all projects
     */
    public List<Project> loadAll();

    /**
     * Delete all projects
     *
     * @return number of projects deleted
     */
    long deleteAll();

}