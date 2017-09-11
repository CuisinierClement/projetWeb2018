/*
 * Creation : 27 avr. 2016
 */
package com.inetpsa.pi201.manageprojects.model.manageprojectsbudgetsresources.project;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.seedstack.seed.it.AbstractSeedIT;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Deliverable;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Project;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.ProjectFactory;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.User;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.UserFactory;

public class ProjectFactoryDefaultIT extends AbstractSeedIT {

    @Inject
    ProjectFactory m_ProjectFactory;
    @Inject
    UserFactory m_UserFactory;

    @Before
    @Test
    public void testInjection() {
        Assertions.assertThat(m_ProjectFactory).isNotNull();
    }

    @Test
    public void projectFactoryDefault() throws ParseException {
        this.createProjectwithID();
        this.createProjectwithoutID();
        this.createProjectEmptyID();

        this.scenarioAddDeliverableToProjectWithParentProject();
        this.scenarioAddDeliverableToProjectWithoutParentProject();

        this.scenarioAddResponsibleToProject();
        this.scenarioAddResponsibleToEmptyProject();

        this.scenarioAddResponsibleToDeliverable();
        this.scenarioAddResponsibleToEmptyDeliverable();
        this.scenarioAddResponsibleToNullProject();
    }

    private void createProjectwithID() {
        String l_Projectname = "Preoject2";
        Project l_Project = m_ProjectFactory.createProject(l_Projectname);
        Assertions.assertThat(l_Project).isNotNull();
    }

    private void createProjectwithoutID() {
        String l_Projectname = null;
        Project l_Project = m_ProjectFactory.createProject(l_Projectname);
        Assertions.assertThat(l_Project).isNull();
    }

    private void createProjectEmptyID() {
        String l_Projectname = "";
        Project l_Project = m_ProjectFactory.createProject(l_Projectname);
        Assertions.assertThat(l_Project).isNull();
    }

    private void scenarioAddDeliverableToProjectWithParentProject() {

        // create a new deliverable
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
        String lComment = "comment1";

        // create a second deliverable
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

        String l_nameDeliverabe2 = "Analyze manage entity project";
        String lComment2 = "comment2";

        // create a new project
        String l_nameProject = "Antidote";
        Project l_parentProject = m_ProjectFactory.createProject(l_nameProject);
        Assertions.assertThat(l_parentProject).isNotNull();

        Deliverable l_Deliverable = m_ProjectFactory.addDeliverableToProject(l_parentProject, l_nameDeliverabe, l_dateStart, l_dateEnd,
                l_dateNewStart, l_dateNewEnd, lComment);
        Assertions.assertThat(l_Deliverable).isNotNull();

        Deliverable l_Deliverable2 = m_ProjectFactory.addDeliverableToProject(l_parentProject, l_nameDeliverabe2, l_dateStart2, l_dateEnd2,
                l_dateNewStart2, l_dateNewEnd2, lComment2);
        Assertions.assertThat(l_Deliverable).isNotNull();

        Deliverable l_Deliverable3 = m_ProjectFactory.addDeliverableToProject(l_parentProject, l_nameDeliverabe, l_dateStart2, l_dateEnd,
                l_dateNewStart, l_dateNewEnd, lComment);
        Assertions.assertThat(l_Deliverable3).isNull();

        Deliverable lLoadDeliverable = m_ProjectFactory.getDeliverable(l_nameDeliverabe, l_parentProject);
        Assertions.assertThat(lLoadDeliverable.getname()).isEqualTo(l_Deliverable.getname());

    }

    private void scenarioAddDeliverableToProjectWithoutParentProject() {

        // create a new deliverable
        String l_nameDeliverabe = "Manual";
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
        String lComment = "comment4";

        Deliverable l_Deliverable = m_ProjectFactory.addDeliverableToProject(null, l_nameDeliverabe, l_dateStart, l_dateEnd, l_dateNewStart,
                l_dateNewEnd, lComment);
        Assertions.assertThat(l_Deliverable).isNull();

    }

    private void scenarioAddResponsibleToProject() {

        // create a user
        String l_login = "u485560";
        String l_name = "Soumare";
        String l_firstName = "Ahmed";

        User l_User = m_UserFactory.createUser(l_login, l_name, l_firstName);
        Assertions.assertThat(l_User).isNotNull();

        // create project
        String l_Projectname = "OrusDiag";
        Project l_Project = m_ProjectFactory.createProject(l_Projectname);
        Assertions.assertThat(l_Project).isNotNull();

        m_ProjectFactory.addResponsibleToProject(l_login, l_Project);

        Assertions.assertThat(l_Project.getResponsable()).isEqualTo(l_login);
    }

    private void scenarioAddResponsibleToEmptyProject() {

        // create a user
        String l_login = "u485560";
        String l_name = "Soumare";
        String l_firstName = "Ahmed";

        User l_User = m_UserFactory.createUser(l_login, l_name, l_firstName);
        Assertions.assertThat(l_User).isNotNull();

        // create an empty project
        String l_Projectname = "";
        Project l_Project = m_ProjectFactory.createProject(l_Projectname);

        m_ProjectFactory.addResponsibleToProject(l_login, l_Project);
        Assertions.assertThat(l_Project).isNull();

    }

    private void scenarioAddResponsibleToNullProject() {

        // create a user
        String l_login = "u485560";
        String l_name = "Soumare";
        String l_firstName = "Ahmed";

        User l_User = m_UserFactory.createUser(l_login, l_name, l_firstName);
        Assertions.assertThat(l_User).isNotNull();

        Project l_Project = null;

        m_ProjectFactory.addResponsibleToProject(l_login, l_Project);
        Assertions.assertThat(l_Project).isNull();
    }

    private void scenarioAddResponsibleToDeliverable() {

        // create a user
        String l_login = "u485560";
        String l_name = "SOUMARE";
        String l_firstName = "Ahmed";

        User l_User = m_UserFactory.createUser(l_login, l_name, l_firstName);
        Assertions.assertThat(l_User).isNotNull();

        // create a project
        String l_nameProject = "Doti";
        Project l_Project = m_ProjectFactory.createProject(l_nameProject);

        // create an empty deliverable
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

        Date lDateStart = dateStartCal.getTime();
        Date lDateEnd = dateEndCal.getTime();
        Date lDateNewStart = dateNewStartCal.getTime();
        Date lDateNewEnd = dateNewEndCal.getTime();

        String lNameDeliverable = "Install documentation";
        String lComment = "comment5";

        Deliverable l_Deliverable = m_ProjectFactory.addDeliverableToProject(l_Project, lNameDeliverable, lDateStart, lDateEnd, lDateNewStart,
                lDateNewEnd, lComment);
        Assertions.assertThat(l_Deliverable).isNotNull();

        m_ProjectFactory.addResponsibleToDeliverable(l_login, l_Deliverable);

        try {
            Assertions.assertThat(l_Deliverable.getResponsableId()).isEqualTo(l_login);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    private void scenarioAddResponsibleToEmptyDeliverable() {

        // create a user
        String l_login = "U485560";
        String l_nameUser = "SOUMARE";
        String l_firstNameUser = "Ahmed";

        User l_User = m_UserFactory.createUser(l_login, l_nameUser, l_firstNameUser);
        Assertions.assertThat(l_User).isNotNull();

        // create a new empty deliverable

        Date lDateStart = new Date();
        Date lDateEnd = new Date();
        Date lDateNewStart = new Date();
        Date lDateNewEnd = new Date();

        String lComment = "comment6";

        String lNameDeliverabe = "";
        String lProjectName = "Doti";

        // create a project
        Project lProject = m_ProjectFactory.createProject(lProjectName);

        Deliverable lDeliverable = m_ProjectFactory.addDeliverableToProject(lProject, lNameDeliverabe, lDateStart, lDateEnd, lDateNewStart,
                lDateNewEnd, lComment);
        Assertions.assertThat(lDeliverable).isNull();

        m_ProjectFactory.addResponsibleToDeliverable(l_login, lDeliverable);
        System.out.println(lProject.getDeliverables());
        Assertions.assertThat(lProject.getDeliverables()).isEmpty();
        ;

    }
}
