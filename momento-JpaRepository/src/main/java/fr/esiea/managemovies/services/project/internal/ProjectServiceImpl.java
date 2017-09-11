/*
 * Creation : 19 avr. 2016
 */
package com.inetpsa.pi201.domains.manageprojects.services.project.internal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.inject.Inject;

import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.transaction.Transactional;

import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Deliverable;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.Project;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.ProjectFactory;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.project.ProjectRepository;
import com.inetpsa.pi201.domains.manageprojects.model.manageprojectsbudgetsresources.user.UserRepository;
import com.inetpsa.pi201.domains.manageprojects.services.project.ProjectService;

public class ProjectServiceImpl implements ProjectService {

    @Inject
    private ProjectRepository mProjectRepository;

    @Inject
    private ProjectFactory mProjectFactory;

    @Inject
    UserRepository mUserRepository;

    @Transactional
    @JpaUnit("manage-domain")
    public void importProjectsFromCsvFile(String pFileName) throws FileNotFoundException {

        BufferedReader lBuffer = null;
        File lFile = new File(pFileName);
        Scanner lScanner = new Scanner(lFile);

        String lLine = "";
        String lCvsSplitBy = ";";
        String lProjectname = "";

        List<Project> lListProject = new ArrayList<Project>();

        try {

            lBuffer = new BufferedReader(new FileReader(pFileName));

            while ((lLine = lBuffer.readLine()) != null) {

                // use comma as separator
                String[] lProjectImport = lLine.split(lCvsSplitBy);

                // Add the project name which is located in column [0]
                lProjectname = lProjectImport[0];

                System.out.println("\n Project [" + lProjectImport[0] + "]");
                // check if the project already exist

                while (lScanner.hasNextLine()) {

                    final String lineFromFile = lScanner.nextLine();
                    System.out.println("line " + lineFromFile);

                    // Check if the Project already exists
                    if ((lListProject.indexOf(lProjectname)) != -1) {
                        System.out.println("The Project already exists");
                        break;
                    }
                    // create a new project object if it doesn't exist
                    Project lProject = mProjectFactory.createProject(lProjectname);

                    // add the created project to the list
                    int lIndex = lListProject.indexOf(lProject);
                    if (lIndex == -1) {

                        lListProject.add(lProject);
                    }

                    break;

                }
                // persist the list of projects in database
                mProjectRepository.persistList(lListProject);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if (lBuffer != null) {
                try {
                    lBuffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Done: ImportProjectsFromCsvFile");
    }

    @Transactional
    @JpaUnit("manage-domain")
    public void importProjectsWithDeliverablesFromCsvFile(String pFileName) throws FileNotFoundException, ParseException {

        BufferedReader lBuffer = null;
        Scanner scanner = new Scanner(new File(pFileName));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        String lLine = "";
        String lCvsSplitBy = ";";
        String lProjectname = "";
        String lDeliverablename = "";
        String lComment = "";

        ArrayList<Project> lListProject = new ArrayList<Project>();

        try {

            lBuffer = new BufferedReader(new FileReader(pFileName));

            while ((lLine = lBuffer.readLine()) != null) {

                // use comma as separator
                String[] lProjectImport = lLine.split(lCvsSplitBy);

                // Add the project name which is located in column [0]
                lProjectname = lProjectImport[0];
                // Add the deliverable name which is located in column [1]
                lDeliverablename = lProjectImport[1];
                // Add the deliverable dateStart which is located in column [2]
                Date l_dateStart = formatter.parse(lProjectImport[2]);
                // Add the deliverable dateEnd which is located in column [3]
                Date l_dateEnd = formatter.parse(lProjectImport[3]);
                // Add the deliverable dateNewStart which is located in column [4]
                Date l_dateNewStart = formatter.parse(lProjectImport[4]);
                // Add the deliverable dateNewEnd which is located in column [5]
                Date l_dateNewEnd = formatter.parse(lProjectImport[5]);
                // Add the deliverable comment which is located in column [6]
                // if (lProjectImport[6] != null && !lProjectImport[6].isEmpty()) {
                // lComment = lProjectImport[6];
                // } else {
                // lComment = "";
                // }

                System.out.println("\n Project Deliverables [Name= " + lProjectImport[0] + " , Name= " + lProjectImport[1] + " , dateStart= "
                        + lProjectImport[2] + " dateEnd= " + lProjectImport[3] + " dateNewStart= " + lProjectImport[4] + " dateNewEnd= "
                        + lProjectImport[5] + "]");

                // check if the project already exist
                checkTest: while (scanner.hasNextLine()) {
                    final String lineFromFile = scanner.nextLine();

                    Project lLoadProject = mProjectRepository.load(lProjectname);
                    // Check if the project already exist
                    if (lLoadProject != null) {
                        System.out.println("The Project already exists");
                        List<Deliverable> lDeliverables = lLoadProject.getDeliverables();

                        for (int i = 0; i < lDeliverables.size(); i++) {
                            if (lDeliverablename.equals(lDeliverables.get(i).getname())) {
                                System.out.println("The Deliverable already exists in the Project");
                                break checkTest;
                            }
                        }
                        // if (lDeliverables.indexOf(lDeliverablename) != -1) {
                        // System.out.println("The Deliverable already exists in the Project");
                        // break;
                        // }
                        Deliverable lDeliverable = mProjectFactory.addDeliverableToProject(lLoadProject, lDeliverablename, l_dateStart, l_dateEnd,
                                l_dateNewStart, l_dateNewEnd, lComment);
                        break;
                    }

                    Project lProject = mProjectFactory.createProject(lProjectname);
                    Deliverable lDeliverable = mProjectFactory.addDeliverableToProject(lProject, lDeliverablename, l_dateStart, l_dateEnd,
                            l_dateNewStart, l_dateNewEnd, lComment);
                    lListProject.add(lProject);

                    break;
                }
                // persist list of project
                mProjectRepository.persistList(lListProject);
                ;

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if (lBuffer != null) {
                try {
                    lBuffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Done: ImportProjectsFromCsvFile");
    }
}
