/**Creation:29 juin 2016*/
package com.inetpsa.pi201.domains.manageprojects.infrastructure.data.project;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.seedstack.business.assembler.FluentAssembler;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.DataImporter;
import org.seedstack.seed.DataSet;
import org.seedstack.seed.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Project;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.ProjectRepository;

@DataSet(group = "manage-domain", name = "projects")
public class ProjectDataImporter implements DataImporter<ProjectDTO> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectDataImporter.class);

    @Inject
    private ProjectRepository mProjectRepository;

    private Set<ProjectDTO> staging = new HashSet<>();

    @Inject
    private FluentAssembler fluentAssembler;

    @Transactional
    @JpaUnit("manage-domain")
    @Override
    public boolean isInitialized() {
        return mProjectRepository.count() != 0;
    }

    @Override
    public void importData(ProjectDTO data) {
        staging.add(data);
    }

    @Transactional
    @JpaUnit("manage-domain")
    @Override
    public void commit(boolean clear) {
        if (clear) {
            LOGGER.info("Clearing projects");
            mProjectRepository.deleteAll();
        }

        LOGGER.info("staging size: " + staging.size());

        for (ProjectDTO projectDTO : staging) {
            mProjectRepository.persist(fluentAssembler.merge(projectDTO).into(Project.class).fromFactory());
        }

        LOGGER.info("Import of projects completed");
        staging.clear();
    }

    @Override
    public void rollback() {
        staging.clear();
        LOGGER.warn("Rollback occurred during project import");
    }
}
