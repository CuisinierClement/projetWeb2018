/*
 * Creation : 15 avr. 2016
 */
package com.inetpsa.pi201.domains.manageprojects.infrastructure.repositories.jpa.project;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.seedstack.jpa.BaseJpaRepository;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Project;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.ProjectRepository;

public class ProjectRepositoryImpl extends BaseJpaRepository<Project, String> implements ProjectRepository {

    public List<Project> loadAll() {
        CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Project> lCriteriaQuery = lCriteriaBuilder.createQuery(Project.class);

        return entityManager.createQuery(lCriteriaQuery.select(lCriteriaQuery.from(Project.class))).getResultList();
    }

    public void persistList(List<Project> pProjects) {

        for (Project l_Project : pProjects) {
            this.persist(l_Project);
        }
    }

    @Override
    public long deleteAll() {
        return entityManager.createQuery("DELETE FROM Project").executeUpdate();
    }

}
