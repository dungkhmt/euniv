package vn.webapp.modules.researchmanagement.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.webapp.modules.researchmanagement.dao.ProjectTasksDAO;
import vn.webapp.modules.researchmanagement.model.ProjectTasks;
import vn.webapp.modules.usermanagement.dao.mDepartmentDAO;
import vn.webapp.modules.usermanagement.dao.mFacultyDAO;
import vn.webapp.modules.usermanagement.model.mDepartment;
import vn.webapp.modules.usermanagement.model.mFaculty;

@Service("ProjectTasksService")
public class ProjectTasksServiceImpl implements ProjectTasksService {
	
	@Autowired
    private ProjectTasksDAO projectTasksDAO;
	
	@Autowired
    private mDepartmentDAO departmentDAO;
	
	@Autowired
    private mFacultyDAO facultyDAO;

    /**
     * Get all list of project statuses
     * @param null
     * @return List
     */
    @Override
    public List<ProjectTasks> getList(){
    	return projectTasksDAO.getList();
    }

    /**
     * 
     */
    @Override
    public ProjectTasks loadAProjectTaskByCode(String sCode){
    	try {
			if (sCode != null) {
				return projectTasksDAO.loadAProjectTaskByCode(sCode);
			}
			return null;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
    }
    
    /**
     * 
     */
    @Override
    public List<ProjectTasks> loadAProjectTaskByProjectCode(String sProjectCode)
    {
    	try {
			if (sProjectCode != null) {
				return projectTasksDAO.loadAProjectTaskByProjectCode(sProjectCode);
			}
			return null;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
    }
    
    /**
     * 
     */
    public List<List<String>> getProjectTaskByProjectCode(String sProjectCode){
    	try {
			if (sProjectCode != null) {
				List<List<String>> projectTasksList = new ArrayList<>();
				Set<String> aListDepartment = new HashSet<String>();
				Set<String> aListFaculty = new HashSet<String>();
				
				List<ProjectTasks> listOfProjectTasks = projectTasksDAO.loadAProjectTaskByProjectCode(sProjectCode);
				if(listOfProjectTasks != null)
				{
					for (ProjectTasks projectTask : listOfProjectTasks) {
						
						aListDepartment.add(projectTask.getStaffProject().getStaff_Department_Code());
						aListFaculty.add(projectTask.getStaffProject().getStaff_Faculty_Code());
					}
					String[] aDepartmentCodes = aListDepartment.toArray(new String[0]);
					String[] aFacultyCodes = aListFaculty.toArray(new String[0]);
					
					List<mDepartment> listDepartments = departmentDAO.loadADepartmentBySetOfCode(aDepartmentCodes);
					List<mFaculty> listFaculties = facultyDAO.getListFacultyBySetOfCode(aFacultyCodes);
					
					for (ProjectTasks projectTask : listOfProjectTasks) {
						List<String> aProjectTask = new ArrayList<>();
						String sFacutlyName 	  = "";
						String sDepartmentName 	  = "";
						aProjectTask.add(projectTask.getStaffProject().getStaff_Name()); // Member's name
						
						for (mFaculty aFaculty : listFaculties) {
							if(projectTask.getStaffProject().getStaff_Faculty_Code().equals(aFaculty.getFaculty_Code()))
							{
								sFacutlyName = aFaculty.getFaculty_Name();
							}
						}
						
						for (mDepartment aDepartment : listDepartments) {
							if(projectTask.getStaffProject().getStaff_Faculty_Code().equals(aDepartment.getDepartment_Code()))
							{
								sDepartmentName = aDepartment.getDepartment_Name();
							}
						}
						
						aProjectTask.add(sFacutlyName); // Member's faculty
						aProjectTask.add(sDepartmentName); // Member's department
						aProjectTask.add(projectTask.getParticipationRoles().getPROJPARTIROLE_Description()); // Member's role
						aProjectTask.add(projectTask.getPRJTSK_Task()); // Task's content
						aProjectTask.add(Integer.toString(projectTask.getPRJTSK_NRBDay())); // Task's working days
						aProjectTask.add(Integer.toString(projectTask.getPRJTSK_Cost())); // Task's cost

						projectTasksList.add(aProjectTask);
					}
					System.out.println(name() + "::getProjectTaskByProjectCode, list members = ");
					for(int i = 0; i < projectTasksList.size(); i++){
						System.out.println(projectTasksList.get(i).get(0) + "\t" + projectTasksList.get(i).get(1));
					}
					//System.exit(-1);
					return projectTasksList;
				}
			}
			return null;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
    }
    public String name(){
    	return "ProjectTasksServiceImpl";
    }
}
