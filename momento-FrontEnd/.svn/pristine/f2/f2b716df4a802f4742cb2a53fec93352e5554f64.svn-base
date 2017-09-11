/*
 * Creation : 26 juil. 2016
 */
package com.inetpsa.pi201.manageprojects.infrastructure.finders.jpa;

import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.core.utils.SeedReflectionUtils;
import org.seedstack.seed.it.SeedITRunner;
import org.seedstack.seed.transaction.Transactional;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Deliverable;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.ProjectFactory;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.ProjectRepository;
import com.inetpsa.pi201.domains.manageprojects.services.project.ProjectService;
import com.inetpsa.pi201.manageprojects.rest.project.DeliverableRepresentation;
import com.inetpsa.pi201.manageprojects.rest.project.ProjectFinder;
import com.inetpsa.pi201.manageprojects.rest.project.ProjectRepresentation;

@JpaUnit("manage-domain")
@Transactional
@RunWith(SeedITRunner.class)
public class ProjectFinderJpaIT {

    @Configuration("com.inetpsa.pi201.domains.manageEntity-Web.importProjectCsvexample")
    private String m_pathFileImportCsv;

    @Configuration("com.inetpsa.pi201.domains.manageEntity-Web.importProjectsWithDeliverablesFromCsv")
    private String m_pathFileImportProjectsWithDeliverablesFromCsv;

    @Inject
    ProjectFactory mProjectFactory;

    @Inject
    ProjectRepository mProjectRepository;

    @Inject
    ProjectService mProjectService;

    @Inject
    ProjectFinder mProjectFinder;

    @Before
    public void setUp() throws FileNotFoundException, ParseException {
        Assertions.assertThat(m_pathFileImportProjectsWithDeliverablesFromCsv).isNotNull();

        mProjectRepository.deleteAll();

        // complete the total path
        // Create a class loader associated to the current class in oder to load resources in the META-INF
        ClassLoader l_classLoader = SeedReflectionUtils.findMostCompleteClassLoader(ProjectFinderJpaIT.class);
        Assertions.assertThat(l_classLoader).isNotNull();

        URL l_URL = l_classLoader.getResource(m_pathFileImportProjectsWithDeliverablesFromCsv);
        Assertions.assertThat(l_URL).isNotNull();
        String l_referenceCompletePathFileCsv = l_URL.getPath();
        l_referenceCompletePathFileCsv = l_referenceCompletePathFileCsv.replace("%20", " ");

        mProjectService.importProjectsWithDeliverablesFromCsvFile(l_referenceCompletePathFileCsv);

        // Check if the projects exist in data base after importing the CSV file
        Assertions.assertThat(mProjectRepository.load("ATTM : Mise sur serveur DSIN")).isNotNull();
        Assertions.assertThat(mProjectRepository.load("ACTIVE : version 5.3")).isNotNull();
        Assertions.assertThat(mProjectRepository.load("Prototype PIV DSEE")).isNotNull();
        Assertions.assertThat(mProjectRepository.load("PLM fenetre : Mars")).isNotNull();

        List<Deliverable> lLoadList2 = mProjectRepository.load("PLM fenetre : Mars").getDeliverables();
        Assertions.assertThat(lLoadList2).isNotEmpty();
        Assertions.assertThat(lLoadList2.size()).isEqualTo(4);
    }

    @Test
    public void testfindProject() {

        System.out.println("1");
        List<ProjectRepresentation> lLoadListProjects = mProjectFinder.findAllProjects();
        Assertions.assertThat(lLoadListProjects.size()).isEqualTo(4);
        System.out.println("2");
        String pExistingProjectId = "ATTM : Mise sur serveur DSIN";
        ProjectRepresentation lExistingProject = mProjectFinder.findProjectById(pExistingProjectId);
        Assertions.assertThat(lExistingProject).isNotNull();

        String pExistingProjectId2 = "ACTIVE : version 5.3";
        ProjectRepresentation lExistingProject2 = mProjectFinder.findProjectById(pExistingProjectId2);
        Assertions.assertThat(lExistingProject2).isNotNull();

        String pExistingProjectId3 = "Prototype PIV DSEE";
        ProjectRepresentation lExistingProject3 = mProjectFinder.findProjectById(pExistingProjectId3);
        Assertions.assertThat(lExistingProject3).isNotNull();

        String pExistingProjectId4 = "PLM fenetre : Mars";
        ProjectRepresentation lExistingProject4 = mProjectFinder.findProjectById(pExistingProjectId4);
        Assertions.assertThat(lExistingProject4).isNotNull();

        String pNoExistingProjectId = "BALLI";
        ProjectRepresentation lNoExistingProject = mProjectFinder.findProjectById(pNoExistingProjectId);
        Assertions.assertThat(lNoExistingProject).isNull();

        List<DeliverableRepresentation> lDeliverableProject1 = lExistingProject.getDeliverables();
        Assertions.assertThat(lDeliverableProject1.size()).isEqualTo(4);

        List<DeliverableRepresentation> lDeliverableProject2 = lExistingProject2.getDeliverables();
        Assertions.assertThat(lDeliverableProject2.size()).isEqualTo(4);

        List<DeliverableRepresentation> lDeliverableProject3 = lExistingProject3.getDeliverables();
        Assertions.assertThat(lDeliverableProject3.size()).isEqualTo(3);

        List<DeliverableRepresentation> lDeliverableProject4 = lExistingProject4.getDeliverables();
        Assertions.assertThat(lDeliverableProject4.size()).isEqualTo(4);
    }
}
