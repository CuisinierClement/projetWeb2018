/*
 * Creation : 19 avr. 2016
 */
package com.inetpsa.pi201.manageprojects.services;

import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.core.utils.SeedReflectionUtils;
import org.seedstack.seed.it.AbstractSeedIT;
import org.seedstack.seed.transaction.Transactional;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Deliverable;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Project;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.ProjectRepository;
import com.inetpsa.pi201.domains.manageprojects.services.project.ProjectService;

public class ProjectServiceIT extends AbstractSeedIT {

    @Inject
    private ProjectService mProjectService;

    @Inject
    private ProjectRepository mProjectRepository;

    @Configuration("com.inetpsa.pi201.domains.manageEntity-JpaRepository.importProjectCsvexample")
    private String mPathFileImportCsv;

    @Configuration("com.inetpsa.pi201.domains.manageEntity-JpaRepository.importProjectsWithDeliverablesFromCsv")
    private String mPathFileImportProjectsWithDeliverablesFromCsv;

    @Before
    @Test
    public void testInjection() {
        Assertions.assertThat(mProjectService).isNotNull();
        Assertions.assertThat(mPathFileImportCsv).isNotNull();
        Assertions.assertThat(mPathFileImportProjectsWithDeliverablesFromCsv).isNotNull();
    }

    @Test
    @Transactional
    @JpaUnit("manage-domain")
    public void projectServicTesteIT() throws FileNotFoundException, ParseException {
        this.importProjectsFromCsvFile();
        this.importProjectsWithDeliverablesFromCsvFile();
    }

    private void importProjectsFromCsvFile() throws FileNotFoundException {

        // complete the total path
        // Create a class loader associated to the current class in oder to load resources in the META-INF
        ClassLoader lClassLoader = SeedReflectionUtils.findMostCompleteClassLoader(ProjectServiceIT.class);
        Assertions.assertThat(lClassLoader).isNotNull();

        URL lURL = lClassLoader.getResource(mPathFileImportCsv);
        Assertions.assertThat(lURL).isNotNull();
        String lReferenceCompletePathFileCsv = lURL.getPath();
        lReferenceCompletePathFileCsv = lReferenceCompletePathFileCsv.replace("%20", " ");

        // Check that the projects don't exist in data base before importing the CSV file
        Assertions.assertThat(mProjectRepository.load("ATTM : Mise sur serveur DSIN")).isNull();
        Assertions.assertThat(mProjectRepository.load("ACTIVE : version 5.3")).isNull();
        Assertions.assertThat(mProjectRepository.load("Prototype PIV DSEE")).isNull();
        Assertions.assertThat(mProjectRepository.load("PLM fenétre : Mars")).isNull();

        // Import the CSV File Projects.csv
        mProjectService.importProjectsFromCsvFile(lReferenceCompletePathFileCsv);

        Assertions.assertThat(lReferenceCompletePathFileCsv).isNotNull();

        // Check if the size of the list of projects is 4 (4 Projects)
        List<Project> lLoadListProjects = mProjectRepository.loadAll();
        Assertions.assertThat(lLoadListProjects.size()).isEqualTo(4);
        // Check if the projects exist in data base after importing the CSV file
        Assertions.assertThat(mProjectRepository.load("ATTM : Mise sur serveur DSIN")).isNotNull();
        Assertions.assertThat(mProjectRepository.load("ACTIVE : version 5.3")).isNotNull();
        Assertions.assertThat(mProjectRepository.load("Prototype PIV DSEE")).isNotNull();
        Assertions.assertThat(mProjectRepository.load("PLM fenetre : Mars")).isNotNull();
    }

    private void importProjectsWithDeliverablesFromCsvFile() throws FileNotFoundException, ParseException {

        // complete the total path
        // Create a class loader associated to the current class in oder to load resources in the META-INF
        ClassLoader lClassLoader = SeedReflectionUtils.findMostCompleteClassLoader(ProjectServiceIT.class);
        Assertions.assertThat(lClassLoader).isNotNull();

        URL lURL = lClassLoader.getResource(mPathFileImportProjectsWithDeliverablesFromCsv);
        Assertions.assertThat(lURL).isNotNull();
        String lReferenceCompletePathFileCsv = lURL.getPath();
        lReferenceCompletePathFileCsv = lReferenceCompletePathFileCsv.replace("%20", " ");

        // Check that the projects exist in data base except for the Balli project which it wasn't created in the first import
        Assertions.assertThat(mProjectRepository.load("ATTM : Mise sur serveur DSIN")).isNotNull();
        Assertions.assertThat(mProjectRepository.load("ACTIVE : version 5.3")).isNotNull();
        Assertions.assertThat(mProjectRepository.load("Prototype PIV DSEE")).isNotNull();
        Assertions.assertThat(mProjectRepository.load("PLM fenetre : Mars")).isNotNull();
        Assertions.assertThat(mProjectRepository.load("BALLI")).isNull();

        mProjectService.importProjectsWithDeliverablesFromCsvFile(lReferenceCompletePathFileCsv);

        // Check if the size of the list of deliverable in each Project
        List<Deliverable> lLoadList = mProjectRepository.load("ATTM : Mise sur serveur DSIN").getDeliverables();
        Assertions.assertThat(lLoadList).isNotEmpty();
        Assertions.assertThat(lLoadList.size()).isEqualTo(4);

        List<Deliverable> lLoadList2 = mProjectRepository.load("PLM fenetre : Mars").getDeliverables();
        Assertions.assertThat(lLoadList2).isNotEmpty();
        Assertions.assertThat(lLoadList2.size()).isEqualTo(4);

        List<Deliverable> lLoadList3 = mProjectRepository.load("ACTIVE : version 5.3").getDeliverables();
        Assertions.assertThat(lLoadList3).isNotEmpty();
        Assertions.assertThat(lLoadList3.size()).isEqualTo(4);

        List<Deliverable> lLoadList4 = mProjectRepository.load("Prototype PIV DSEE").getDeliverables();
        Assertions.assertThat(lLoadList4).isNotEmpty();
        Assertions.assertThat(lLoadList4.size()).isEqualTo(3);

    }

}
