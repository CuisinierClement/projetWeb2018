/*
 * Creation : 15 avr. 2016
 */
package com.inetpsa.pi201.manageprojects.infrastructure.repositories.jpa.project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.it.AbstractSeedIT;
import org.seedstack.seed.transaction.Transactional;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Deliverable;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Project;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.ProjectFactory;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.ProjectRepository;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.User;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.UserFactory;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.UserRepository;

public class ProjectRepositoryImplIT extends AbstractSeedIT {

    @Inject
    private ProjectRepository m_ProjectRepository;

    @Inject
    private UserRepository m_UserRepository;

    @Inject
    private ProjectFactory m_ProjectFactory;

    @Inject
    private UserFactory m_UserFactory;

    @Before
    public void injectionTest() {

        Assertions.assertThat(m_ProjectRepository).isNotNull();

        Assertions.assertThat(m_ProjectFactory).isNotNull();
    }

    @Test
    @Transactional
    @JpaUnit("manage-domain")
    public void saveProjects() {
        // List of methods to test
        // m_ProjectRepository.delete(id);
        // m_ProjectRepository.delete(aggregate);
        // m_ProjectRepository.persist(aggregate);
        // m_ProjectRepository.persistList(p_Users);
        // m_ProjectRepository.load(id)
        // m_ProjectRepository.loadAll();
        // m_ProjectRepository.save(aggregate);

        this.checkDataBaseOfProjectIsEmpty();
        this.updateProjectInDataBase();
        this.scenarioOnePersistOneProject();
        this.scenarioOnePersistOfProjectList();

    }

    private void checkDataBaseOfProjectIsEmpty() {
        m_ProjectRepository.clear();
        List<Project> lexistingListProject = m_ProjectRepository.loadAll();
        Assertions.assertThat(lexistingListProject).hasSize(0);
    }

    private void updateProjectInDataBase() {

        // Create project
        List<Project> l_ListProject = this.createListOfProject();

        Assertions.assertThat(l_ListProject).hasSize(2);

        m_ProjectRepository.persistList(l_ListProject);

        List<Project> lExistingListProject = m_ProjectRepository.loadAll();
        Assertions.assertThat(lExistingListProject).hasSize(2);

        String lname = "Expression du besoin";
        Project lproject = m_ProjectRepository.load(lname);
        Assertions.assertThat(lproject.getDeliverables()).isNotNull();

        String lname2 = "Développement";
        Project lproject2 = m_ProjectRepository.load(lname2);
        Assertions.assertThat(lproject2.getDeliverables()).isEmpty();

        // Assertions.assertThat(lExistingListProject.get(0).getname()).isNotNull();
        // Assertions.assertThat(lExistingListProject.get(0).getResponsable()).isNotNull();
        // Assertions.assertThat(lExistingListProject.get(0).getDeliverables()).isNotNull();
        //
        // String lExistingNameDeliverable = lExistingListProject.get(1).getDeliverables().get(0).getname();
        // Assertions.assertThat(lExistingNameDeliverable).isEqualTo("Install documentation");
        //
        // String lExistingNameDeliverable2 = lExistingListProject.get(1).getDeliverables().get(1).getname();
        // Assertions.assertThat(lExistingNameDeliverable2).isEqualTo("Install IDE");
    }

    private void scenarioOnePersistOneProject() {

        // The data base is normally empty ( create mode..)
        String l_ProjectName = "Projet web";

        // try to load a user in empty data base
        Assertions.assertThat(m_ProjectRepository.load(l_ProjectName)).isNull();

        // create a project
        Project l_Project_1 = m_ProjectFactory.createProject(l_ProjectName);

        // Persist the Project on data base
        m_ProjectRepository.persist(l_Project_1);

        // verify that the user exist in data base
        Assertions.assertThat(m_ProjectRepository.load(l_ProjectName)).isNotNull();

        // try to persist the loaded project from data base : Normally no error
        Project l_Project_2 = m_ProjectFactory.createProject(l_ProjectName);
        Assertions.assertThat(l_Project_2).isNotNull();
        // Assertions.assertThat(m_ProjectRepository.persist(l_Project_2)).isNotNull();

        // update Project2 in the data base
        Assertions.assertThat(m_ProjectRepository.save(l_Project_2)).isNotNull();

        // delete an existing project in the persistence list
        m_ProjectRepository.delete(l_ProjectName);

        // verify that the project have been deleted
        Assertions.assertThat(m_ProjectRepository.load(l_ProjectName)).isNull();

    }

    private void scenarioOnePersistOfProjectList() {
        // create an empty list of project
        List<Project> l_listOfproject = new ArrayList<Project>();

        String l_Project_1_name = "Projet Android";
        Project l_Project_1 = m_ProjectFactory.createProject(l_Project_1_name);
        Assertions.assertThat(l_Project_1).isNotNull();

        String l_Project_2_name = "Projet ";
        Project l_Project_2 = m_ProjectFactory.createProject(l_Project_2_name);
        Assertions.assertThat(l_Project_2).isNotNull();

        String l_Project_3_name = "Manage Entity  by Ahmed&Théo";
        Project l_Project_3 = m_ProjectFactory.createProject(l_Project_3_name);
        Assertions.assertThat(l_Project_3).isNotNull();

        // add projects to list
        l_listOfproject.add(l_Project_1);
        l_listOfproject.add(l_Project_2);
        l_listOfproject.add(l_Project_3);

        // persist list of project in data base
        m_ProjectRepository.persistList(l_listOfproject);

        // verify that all the project list exist in data base
        List<Project> l_listOfprojectFromDataBase = m_ProjectRepository.loadAll();

        int l_index = l_listOfprojectFromDataBase.indexOf(l_Project_1);

        try {
            Assertions.assertThat(l_listOfprojectFromDataBase.get(l_index)).isEqualTo(l_Project_1);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    private List<Project> createListOfProject() {
        List<Project> l_listProject = new ArrayList<Project>();

        String l_projectName = "Expression du besoin";
        String l_projectName2 = "Développement";

        // create a list of project
        Project l_Project = m_ProjectFactory.createProject(l_projectName);
        Project l_Project2 = m_ProjectFactory.createProject(l_projectName2);

        // first deliverable
        Calendar dateStartCal = Calendar.getInstance();
        dateStartCal.set(Calendar.YEAR, 2016);
        dateStartCal.set(Calendar.MONTH, 05);
        dateStartCal.set(Calendar.DAY_OF_MONTH, 01);

        Calendar dateEndCal = Calendar.getInstance();
        dateEndCal.set(Calendar.YEAR, 2016);
        dateEndCal.set(Calendar.MONTH, 05);
        dateEndCal.set(Calendar.DAY_OF_MONTH, 30);

        Calendar dateNewStartCal = Calendar.getInstance();
        dateNewStartCal.set(Calendar.YEAR, 2016);
        dateNewStartCal.set(Calendar.MONTH, 06);
        dateNewStartCal.set(Calendar.DAY_OF_MONTH, 01);

        Calendar dateNewEndCal = Calendar.getInstance();
        dateNewEndCal.set(Calendar.YEAR, 2016);
        dateNewEndCal.set(Calendar.MONTH, 06);
        dateNewEndCal.set(Calendar.DAY_OF_MONTH, 30);

        Date l_dateStart = dateStartCal.getTime();
        Date l_dateEnd = dateEndCal.getTime();
        Date l_dateNewStart = dateNewStartCal.getTime();
        Date l_dateNewEnd = dateNewEndCal.getTime();

        String l_nameDeliverabe = "Install documentation";
        String lComment = "comment 7";

        Deliverable l_firstDeliverable = m_ProjectFactory.addDeliverableToProject(l_Project, l_nameDeliverabe, l_dateStart, l_dateEnd, l_dateNewStart,
                l_dateNewEnd, lComment);

        // second deliverable
        Calendar dateStartCal2 = Calendar.getInstance();
        dateStartCal2.set(Calendar.YEAR, 2016);
        dateStartCal2.set(Calendar.MONTH, 05);
        dateStartCal2.set(Calendar.DAY_OF_MONTH, 01);

        Calendar dateEndCal2 = Calendar.getInstance();
        dateEndCal2.set(Calendar.YEAR, 2016);
        dateEndCal2.set(Calendar.MONTH, 05);
        dateEndCal2.set(Calendar.DAY_OF_MONTH, 30);

        Calendar dateNewStartCal2 = Calendar.getInstance();
        dateNewStartCal2.set(Calendar.YEAR, 2016);
        dateNewStartCal2.set(Calendar.MONTH, 06);
        dateNewStartCal2.set(Calendar.DAY_OF_MONTH, 01);

        Calendar dateNewEndCal2 = Calendar.getInstance();
        dateNewEndCal2.set(Calendar.YEAR, 2016);
        dateNewEndCal2.set(Calendar.MONTH, 06);
        dateNewEndCal2.set(Calendar.DAY_OF_MONTH, 30);

        Date l_dateStart2 = dateStartCal.getTime();
        Date l_dateEnd2 = dateEndCal.getTime();
        Date l_dateNewStart2 = dateNewStartCal.getTime();
        Date l_dateNewEnd2 = dateNewEndCal.getTime();

        String l_nameDeliverabe2 = "Install IDE";
        String lComment2 = "comment8";

        Deliverable lSecondDeliverable = m_ProjectFactory.addDeliverableToProject(l_Project, l_nameDeliverabe2, l_dateStart2, l_dateEnd2,
                l_dateNewStart2, l_dateNewEnd2, lComment2);

        // create manager of the project
        String l_loginProject = "17485560";
        String l_firstNameProject = "Ahmed Salem";
        String l_nameProject = "Soumare";

        User l_UserProject = m_UserFactory.createUser(l_loginProject, l_nameProject, l_firstNameProject);
        m_UserRepository.persist(l_UserProject);
        // add the manager to the project
        m_ProjectFactory.addResponsibleToProject(l_loginProject, l_Project);
        m_ProjectFactory.addResponsibleToProject(l_loginProject, l_Project2);

        // create manager of the first deliverable
        String l_loginDeliverable = "1211256";
        String l_firstNameDeliverable = "Théo";
        String l_nameDeliverable = "FOUCHE";

        User l_UserDeliverable = m_UserFactory.createUser(l_loginDeliverable, l_nameDeliverable, l_firstNameDeliverable);
        m_UserRepository.persist(l_UserDeliverable);
        // add the manager to the first deliverable
        m_ProjectFactory.addResponsibleToDeliverable(l_loginDeliverable, l_firstDeliverable);

        // create manager of the second deliverable
        String l_loginDeliverable2 = "13484302";
        String l_firstNameDeliverable2 = "Eric";
        String l_nameDeliverable2 = "NAVARRO";

        User l_UserDeliverable2 = m_UserFactory.createUser(l_loginDeliverable2, l_nameDeliverable2, l_firstNameDeliverable2);
        m_UserRepository.persist(l_UserDeliverable2);
        // add the manager to the second deliverable
        m_ProjectFactory.addResponsibleToDeliverable(l_loginDeliverable2, lSecondDeliverable);

        l_listProject.add(l_Project);
        l_listProject.add(l_Project2);

        return l_listProject;

    }
}
