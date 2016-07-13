/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.controller.cp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



/**
 * Customize
 */
import vn.webapp.controller.BaseWeb;
import vn.webapp.modules.mastermanagement.algorithms.Schedule;
import vn.webapp.modules.mastermanagement.controller.cpservice.PDFGenerator;
import vn.webapp.modules.mastermanagement.model.mmDefenseSession;
import vn.webapp.modules.mastermanagement.model.mmExternalStaff;
import vn.webapp.modules.mastermanagement.model.mmJuryExternalMember;
import vn.webapp.modules.mastermanagement.model.mmJuryMember;
import vn.webapp.modules.mastermanagement.model.mmJuryRoom;
import vn.webapp.modules.mastermanagement.model.mmJurySlot;
import vn.webapp.modules.mastermanagement.model.mmMasterThesis;
import vn.webapp.modules.mastermanagement.model.mmMasterDefenseJuryThesis;
import vn.webapp.modules.mastermanagement.model.mmRooms;
import vn.webapp.modules.mastermanagement.model.mmSpecializationKeyword;
import vn.webapp.modules.mastermanagement.model.mmStaff;
import vn.webapp.modules.mastermanagement.service.mmDefenseSessionService;
import vn.webapp.modules.mastermanagement.service.mmExternalStaffService;
import vn.webapp.modules.mastermanagement.service.mmExternalStaffServiceImpl;
import vn.webapp.modules.mastermanagement.service.mmJuryExternalMemberService;
import vn.webapp.modules.mastermanagement.service.mmJuryMemberService;
import vn.webapp.modules.mastermanagement.service.mmJuryRoomService;
import vn.webapp.modules.mastermanagement.service.mmJurySlotService;
import vn.webapp.modules.mastermanagement.service.mmMasterClassService;
import vn.webapp.modules.mastermanagement.service.mmMasterDefenseJuryService;
import vn.webapp.modules.mastermanagement.service.mmMasterThesisService;
import vn.webapp.modules.mastermanagement.service.mmRoomsService;
import vn.webapp.modules.mastermanagement.service.mmSpecializationKeywordService;
import vn.webapp.modules.mastermanagement.service.mmStaffService;

@Controller("mmSchedule")
@RequestMapping(value = {"/mm"})
public class mmScheduleController extends BaseWeb {
	   
    @Autowired
    private mmStaffService mmstaffService;
    
    @Autowired
    private mmMasterClassService mmmasterClassService;
    
    @Autowired
    private mmMasterThesisService mmmasterThesisService;
    
    @Autowired
    private mmSpecializationKeywordService mmspecializationKeywordService;
    
    @Autowired
    private mmMasterDefenseJuryService mmmasterDefenseJuryService;
    
    @Autowired
    private mmRoomsService mmroomsService;
 
    @Autowired 
    private mmJuryMemberService mmjuryMemberService;
    
    @Autowired 
    private mmJuryExternalMemberService mmjuryExternalMemberService;
        
    @Autowired 
    private mmExternalStaffService mmexternalStaffService;
 
    @Autowired
    private mmJuryRoomService mmjuryRoomService;
    
    @Autowired
    private mmJurySlotService mmjurySlotService;
    
    @Autowired
    private mmDefenseSessionService mmdefenseSessionService;

    static final String scheduling = "active";
    static final boolean disableHeader = true;
    
    /**
    * Scheduling
    * @param model
    * @param session
    * @return
    */
    
    @RequestMapping(value = "/jurymembers/{dsid}", method = RequestMethod.GET)
    public String juryMember(ModelMap model, @PathVariable("dsid") int dsid, HttpSession session){
    	String userCode = session.getAttribute("currentUserCode").toString();
    	//List<mmStaff> listStaff = mmstaffService.listStaffs();
    	List<mmStaff> listStaff = mmstaffService.listStaffsByFaculty("SOICT");
    	List<mmStaff> listMembers = new ArrayList<mmStaff>();
    	mmDefenseSession defenseSession = mmdefenseSessionService.getDefenseSessionById(dsid);
    	List<mmJuryMember> listRawJuryMembers = mmjuryMemberService.listJuryMembers(defenseSession.getDEFSESS_Code(), userCode);
    	List<mmStaff> listJuryMembers = new ArrayList<mmStaff>();
    	
    	if(listRawJuryMembers != null){
  		    for(mmStaff st:listStaff){
  			   int flag = 0;
  			   String currentStaffCode = st.getStaff_Code();
  			   for(mmJuryMember jm:listRawJuryMembers){
  				   if(jm.getJuryMem_MemberCode().equals(currentStaffCode)){
  					   flag = 1;
  					   break;
  				   }
  			   }
  			   if (flag == 0){
  				   listMembers.add(st);
  			   }else{
  				   listJuryMembers.add(st);
  			   }
  		    }
  		    model.put("listMembers", listMembers);     	
  	    }
    	else{
    		model.put("listMembers", listStaff);
    	}
    	
    	
    	model.put("department", mmstaffService.loadStaffByStaffCode(userCode).getDepartment().getDepartment_Code());
    	model.put("defenseSession", defenseSession);
    	model.put("listJuryMembers", listJuryMembers);
    	model.put("disableHeader", mmScheduleController.disableHeader);
    	return "mm.juryMembers";
    }
    
    @RequestMapping(value = "/juryexternalmembers/{dsid}", method = RequestMethod.GET)
    public String juryExternalMember(ModelMap model, @PathVariable("dsid") int dsid, HttpSession session){
    	String userCode = session.getAttribute("currentUserCode").toString();
    	List<mmExternalStaff> listStaff = mmexternalStaffService.listExternalStaffs();
    	List<mmExternalStaff> listMembers = new ArrayList<mmExternalStaff>();
    	mmDefenseSession defenseSession = mmdefenseSessionService.getDefenseSessionById(dsid);
    	List<mmJuryExternalMember> listRawJuryExternalMembers = mmjuryExternalMemberService.listJuryExternalMembers(defenseSession.getDEFSESS_Code(), userCode);
    	List<mmExternalStaff> listJuryExternalMembers = new ArrayList<mmExternalStaff>();
    	if(listRawJuryExternalMembers != null){
  		    for(mmExternalStaff st:listStaff){
  			   int flag = 0;
  			   String currentStaffCode = st.getEXTSTAF_Code();
  			   for(mmJuryExternalMember jm:listRawJuryExternalMembers){
  				   if(jm.getJEM_MemberCode().equals(currentStaffCode)){
  					   flag = 1;
  					   break;
  				   }
  			   }
  			   if (flag == 0){
				   listMembers.add(st);
			   }else{
				   listJuryExternalMembers.add(st);
			   }
  		    }
  		    model.put("listMembers", listMembers);     	
  	    }
    	else{
    		model.put("listMembers", listStaff);
    	}
    	
    	
    	model.put("department", mmstaffService.loadStaffByStaffCode(userCode).getDepartment().getDepartment_Code());
    	model.put("defenseSession", defenseSession);
    	model.put("listJuryMembers", listJuryExternalMembers);
    	model.put("disableHeader", mmScheduleController.disableHeader);
    	return "mm.juryExternalMembers";
    }
    
    @RequestMapping(value = "/juryrooms/{dsid}", method = RequestMethod.GET)
    public String juryRooms(ModelMap model, @PathVariable("dsid") int dsid, HttpSession session){
    	String userCode = session.getAttribute("currentUserCode").toString();
    	mmDefenseSession defenseSession = mmdefenseSessionService.getDefenseSessionById(dsid);
    	List<mmJuryRoom> listJuryRooms = mmjuryRoomService.listJuryRooms(defenseSession.getDEFSESS_Code(),userCode);
    	List<mmRooms> listAllRooms = mmroomsService.listRooms();
    	List<mmRooms> listRooms = new ArrayList<mmRooms>();
    	
    	if(listJuryRooms != null){
  		    for(mmRooms st:listAllRooms){
  			   int flag = 0;
  			   String currentRoomsCode = st.getR_Code();
  			   for(mmJuryRoom jm:listJuryRooms){
  				   if(jm.getJuryRoom_Code().equals(currentRoomsCode)){
  					   flag = 1;
  					   break;
  				   }
  			   }
  			   if (flag == 0)
  				   listRooms.add(st);
  		    }
  		    model.put("listRooms", listRooms);     	
  	    }
    	else{
    		model.put("listRooms", listAllRooms);
    	}
    	
    	System.out.println(userCode);
    	model.put("department", mmstaffService.loadStaffByStaffCode(userCode).getDepartment().getDepartment_Code());
    	model.put("disableHeader", mmScheduleController.disableHeader);
    	model.put("defenseSession", defenseSession);
    	model.put("listJuryRooms", listJuryRooms);    	
    	return "mm.juryRooms";
    }
    @RequestMapping(value = "/juryslots/{dsid}", method = RequestMethod.GET)
    public String jurySlots(ModelMap model,@PathVariable("dsid") int dsid,HttpSession session){
    	String userCode = session.getAttribute("currentUserCode").toString();
    	mmDefenseSession defenseSession = mmdefenseSessionService.getDefenseSessionById(dsid);
    	List<mmDefenseSession> listDefenseSessions = mmmasterThesisService.listDefenseSession();
    	List<mmJurySlot> listJurySlots = mmjurySlotService.listJurySlots(userCode);
    	
    	model.put("department", mmstaffService.loadStaffByStaffCode(userCode).getDepartment().getDepartment_Code());
    	model.put("defenseSession", defenseSession);
    	model.put("disableHeader", mmScheduleController.disableHeader);
    	model.put("listDefenseSessions", listDefenseSessions);
    	model.put("listJurySlots", listJurySlots);
    	return "mm.jurySlots";
    }
 
   
   @RequestMapping(value = "/scheduling/{dsid}", method = RequestMethod.GET)
   public String scheduling(ModelMap model, @PathVariable("dsid") int dsid, HttpSession session) { 
	   String userCode = session.getAttribute("currentUserCode").toString();
	   mmDefenseSession defenseSession = mmdefenseSessionService.getDefenseSessionById(dsid);
	   List<mmMasterDefenseJuryThesis> listMasterDefenseJuryThesis = mmmasterDefenseJuryService.listMasterDefenseJuryThesisByDefenseSession(defenseSession.getDEFSESS_Code(), userCode);
	   List<mmMasterThesis> listMasterThesis= mmmasterThesisService.listMasterThesis();
	   List<mmMasterThesis> listMasterThesisSatisfy = new ArrayList<mmMasterThesis>();
	   
	   if(listMasterDefenseJuryThesis != null){
		   for(mmMasterThesis mt:listMasterThesis){
			   int flag = 0;
			   String currentThesisCode = mt.getThesis_Code();
			   if(listMasterDefenseJuryThesis != null){
				   for(mmMasterDefenseJuryThesis smt:listMasterDefenseJuryThesis){
					   if(smt.getMasterThesis().getThesis_Code().equals(currentThesisCode)){
						   flag = 1;
						   break;
					   }
				   }
			   }
			   if (flag == 0)
				   listMasterThesisSatisfy.add(mt);
		   }
	   }
	   
	   model.put("department", mmstaffService.loadStaffByStaffCode(userCode).getDepartment().getDepartment_Name());
	   model.put("listMasterDefenseJuryThesis", listMasterDefenseJuryThesis);
	   model.put("listMasterThesis", listMasterThesisSatisfy);
	   model.put("defenseSession", defenseSession);
	   model.put("scheduling", mmScheduleController.scheduling);
	   model.put("disableHeader", mmScheduleController.disableHeader);
	   return "mm.scheduling";
   }
   
   /**
    * Setting juries
    * @param model
    * @param session
    * @return
    */
   @RequestMapping(value = "/settingjuries/{dsid}", method = RequestMethod.GET)
   public String settingJuries(ModelMap model, @PathVariable("dsid") int dsid, HttpSession session) {
	   String userCode = session.getAttribute("currentUserCode").toString();
	   mmDefenseSession defenseSession = mmdefenseSessionService.getDefenseSessionById(dsid);
	   List<mmMasterDefenseJuryThesis> listMasterDefenseJuryThesis = mmmasterDefenseJuryService.listMasterDefenseJuryThesisByDefenseSession(defenseSession.getDEFSESS_Code(), userCode);
	   List<mmJuryRoom> listJuryRooms = mmjuryRoomService.listJuryRooms(defenseSession.getDEFSESS_Code(),userCode);
	   List<mmJurySlot> listJurySlot = mmjurySlotService.listJurySlots(defenseSession.getDEFSESS_Code(),userCode);
	   List<mmJuryMember> listRawJuryMembers = mmjuryMemberService.listJuryMembers(defenseSession.getDEFSESS_Code(), userCode);
	   List<mmJuryExternalMember> listRawJuryExternalMembers = mmjuryExternalMemberService.listJuryExternalMembers(defenseSession.getDEFSESS_Code(), userCode);
   	   List<mmStaff> listInnerJuryMembers = new ArrayList<mmStaff>();
   	   List<mmExternalStaff> listOuterJuryMembers = new ArrayList<mmExternalStaff>();
	   for(mmJuryMember jurymember:listRawJuryMembers){
		   listInnerJuryMembers.add(mmstaffService.loadStaffByStaffCode(jurymember.getJuryMem_MemberCode()));
	   }
	   for(mmJuryExternalMember juryexternalmember:listRawJuryExternalMembers){
		   listOuterJuryMembers.add(mmexternalStaffService.getByExternalStaffCode(juryexternalmember.getJEM_MemberCode()));
	   }
		 
	   model.put("defenseSession", defenseSession);		 
	   model.put("department", mmstaffService.loadStaffByStaffCode(userCode).getDepartment().getDepartment_Name());
	   model.put("listInnerJuryMembers", listInnerJuryMembers);
	   model.put("listOuterJuryMembers", listOuterJuryMembers);
	   model.put("listJuryRooms", listJuryRooms);
	   model.put("listJurySlot", listJurySlot);	   
	   model.put("listMasterDefenseJuryThesis", listMasterDefenseJuryThesis);
	   	   
	   model.put("scheduling", mmScheduleController.scheduling);
	   model.put("disableHeader", mmScheduleController.disableHeader);
	   return "mm.settingjuries";
   }
      
   	@ResponseBody
	@RequestMapping(value = "/save-defense", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String savedefense(@RequestParam(value = "defensesession") String defensesession,
								   @RequestParam(value = "listthesis") String listrawthesis,
	   							   @RequestParam(value = "listexaminer1") String listrawexaminer1,
	   							   @RequestParam(value = "listexaminer2") String listrawexaminer2,
	   							   @RequestParam(value = "listpresident") String listrawpresident,
	   							   @RequestParam(value = "listsecretary") String listrawsecretary,
	   							   @RequestParam(value = "listcommissioner") String listrawcommissioner,
	   							   @RequestParam(value = "listslot") String listrawslot,
	   							   @RequestParam(value = "listroom") String listrawroom,
	   							   HttpSession session) {
   		
   	   String userCode = session.getAttribute("currentUserCode").toString();
   	   String[] listthesis = listrawthesis.split(",");
 	   String[] listexaminer1 = listrawexaminer1.split(",");
 	   String[] listexaminer2 = listrawexaminer2.split(",");
 	   String[] listpresident = listrawpresident.split(",");
 	   String[] listsecretary = listrawsecretary.split(","); 
 	   String[] listcommissioner = listrawcommissioner.split(",");
 	   String[] listslot = listrawslot.split(","); 	   
 	   String[] listroom = listrawroom.split(",");
   		
 	   for (int i = 0; i < listthesis.length; i++) {
 		   if(listexaminer1[i].equals("null")){
 			  listexaminer1[i] = "";
 		   }
 		   if(listexaminer2[i].equals("null")){
 			  listexaminer2[i] = "";
 		   }
 		   if(listpresident[i].equals("null")){
 			  listpresident[i] = "";
 		   }
 		   if(listsecretary[i].equals("null")){
			  listsecretary[i] = "";
		   }
 		   if(listcommissioner[i].equals("null")){
			  listcommissioner[i] = "";
		   }
 		   if(listslot[i].equals("null")){
			  listslot[i] = "";
		   }
 		   if(listroom[i].equals("null")){
			  listroom[i] = "";
		   }
 		   boolean isUpdated = mmmasterDefenseJuryService.updateAMasterDefenseJuryThesis(listthesis, listexaminer1, listexaminer2, listpresident, listsecretary, listcommissioner, listslot, listroom, null, userCode, defensesession);
 		   if(isUpdated){
 			   return "Bạn đã lưu thành công thông tin hội đồng";
 		   }
 	   }
		
		return "";
	}
   	
	@ResponseBody
	@RequestMapping(value = "/autoSchedule", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String autoSchedule(@RequestParam(value = "defensesession") String defensesession,
							   @RequestParam(value = "listsupervisor") String listrawsupervisor,
							   @RequestParam(value = "listthesis") String listrawthesis,
   							   @RequestParam(value = "listexaminer1") String listrawexaminer1,
   							   @RequestParam(value = "listexaminer2") String listrawexaminer2,
   							   @RequestParam(value = "listpresident") String listrawpresident,
   							   @RequestParam(value = "listsecretary") String listrawsecretary,
   							   @RequestParam(value = "listcommissioner") String listrawcommissioner,
   							   @RequestParam(value = "listslot") String listrawslot,
   							   @RequestParam(value = "listroom") String listrawroom,
   							   HttpSession session) {
   		
   	   String userCode = session.getAttribute("currentUserCode").toString();
   	   String[] listsupervisor = listrawsupervisor.split(",");
   	   String[] listthesis = listrawthesis.split(",");
 	   String[] listexaminer1 = listrawexaminer1.split(",");
 	   String[] listexaminer2 = listrawexaminer2.split(",");
 	   String[] listpresident = listrawpresident.split(",");
 	   String[] listsecretary = listrawsecretary.split(","); 
 	   String[] listcommissioner = listrawcommissioner.split(",");
 	   String[] listslot = listrawslot.split(","); 	   
 	   String[] listroom = listrawroom.split(",");
   		
 	   
 	   List<mmJuryMember> listAllJuryMembers = mmjuryMemberService.listJuryMembers(defensesession, userCode);
	   List<mmJuryExternalMember> listAllJuryExternalMembers = mmjuryExternalMemberService.listJuryExternalMembers(defensesession, userCode);
	   
	   List<mmStaff> hostProfessors = new ArrayList<mmStaff>();
	   List<mmExternalStaff> guestProfessors = new ArrayList<mmExternalStaff>();
	   
	   
	   for(mmJuryMember juryMember:listAllJuryMembers){
		   hostProfessors.add(mmstaffService.loadStaffByStaffCode(juryMember.getJuryMem_MemberCode()));
	   }
	   for(mmJuryExternalMember juryExternalMember:listAllJuryExternalMembers){
		   guestProfessors.add(mmexternalStaffService.getByExternalStaffCode(juryExternalMember.getJEM_MemberCode()));
	   }
	   
	   
	   List<mmJurySlot> listAllJurySlot = mmjurySlotService.listJurySlots(defensesession,userCode);		  
	   List<mmJuryRoom> listAllJuryRooms = mmjuryRoomService.listJuryRooms(defensesession,userCode);
	   	
	   List<String> listInternalProfessorCode = new ArrayList<String>();
	   for(int i=0; i<listAllJuryMembers.size(); i++){
		   listInternalProfessorCode.add(listAllJuryMembers.get(i).getJuryMem_MemberCode());
	   }
	   
	   //supervisor that are not in list jury members
	   for(int i=0; i<listsupervisor.length; i++){
		   if(!listInternalProfessorCode.contains(listsupervisor[i])){
			   listInternalProfessorCode.add(listsupervisor[i]);
		   }
	   }
	   
	   
 	  	HashMap<String, Integer> hostProfessorIndex = new HashMap<String, Integer>();
		for(int i=0; i<listInternalProfessorCode.size(); i++){
			hostProfessorIndex.put(listInternalProfessorCode.get(i), i);
			//System.out.println("Put hostProfessor "+ listInternalProfessorCode.get(i) +"to index" +i);
		}			
		
		HashMap<String, Integer> guestProfessorIndex = new HashMap<String, Integer>();
		for(int i=0; i<listAllJuryExternalMembers.size(); i++){
			guestProfessorIndex.put(listAllJuryExternalMembers.get(i).getJEM_MemberCode(), i);
		}
		
		HashMap<String, Integer> slotIndex = new HashMap<String, Integer>();
		for(int i=0; i<listAllJurySlot.size(); i++){
			slotIndex.put(listAllJurySlot.get(i).getJurySlot_Code(), i);
		}
		
		HashMap<String, Integer> roomIndex = new HashMap<String, Integer>();
		for(int i=0; i<listAllJuryRooms.size(); i++){
			roomIndex.put(listAllJuryRooms.get(i).getJuryRoom_Code(), i);
		}
		
		int[][] hostProfessorScore = new int[listthesis.length][listAllJuryMembers.size()];
		int[][] guestProfessorScore = new int[listthesis.length][listAllJuryExternalMembers.size()];
		
		List<List<String>> listInternalProfessorSpeKw = new ArrayList<List<String>>();
		List<List<String>> listExternalProfessorSpeKw = new ArrayList<List<String>>();
		
		//Get list internal member specialization
		for(int i=0; i<listAllJuryMembers.size();i++){
			List<String> listSpeKwString = new ArrayList<String>();
			List<mmSpecializationKeyword> listSpeKw = mmspecializationKeywordService.loadStaffSpecializationKeywordList(listAllJuryMembers.get(i).getJuryMem_MemberCode());
			if(listSpeKw != null){
				for(mmSpecializationKeyword speKw:listSpeKw){
					listSpeKwString.add(speKw.getKW_Code());
				}
			}
			listInternalProfessorSpeKw.add(listSpeKwString);
		}
		
		//Get list external member specialization
		for(int i=0; i<listAllJuryExternalMembers.size();i++){
			List<String> listSpeKwString = new ArrayList<String>();
			List<mmSpecializationKeyword> listSpeKw = mmspecializationKeywordService.loadStaffSpecializationKeywordList(listAllJuryExternalMembers.get(i).getJEM_MemberCode());
			if(listSpeKw != null){
				for(mmSpecializationKeyword speKw:listSpeKw){
					listSpeKwString.add(speKw.getKW_Code());
				}
			}
			listExternalProfessorSpeKw.add(listSpeKwString);
		}
				
		for(int i=0; i<listthesis.length;i++){
			List<mmSpecializationKeyword> listMasterSpeKw = mmspecializationKeywordService.loadMasterThesisSpecializationKeywordList(listthesis[i]);
			
			for(int j=0; j<listInternalProfessorSpeKw.size();j++){
				int countInternal = 0;					
				for(mmSpecializationKeyword speKw:listMasterSpeKw){						
					if(listInternalProfessorSpeKw.get(j).contains(speKw.getKW_Code())){
						countInternal++;
					}
				}
				hostProfessorScore[i][j] = countInternal;	
				//System.out.println("hostProfessorScore["+i+"]["+j+"]="+countInternal);
			}
			
			for(int j=0; j<listExternalProfessorSpeKw.size();j++){
				int countExternal = 0;					
				for(mmSpecializationKeyword speKw:listMasterSpeKw){						
					if(listExternalProfessorSpeKw.get(j).contains(speKw.getKW_Code())){
						countExternal++;
					}
				}
				guestProfessorScore[i][j] = countExternal;				
				//System.out.println("externalProfessorScore["+i+"]["+j+"]="+countExternal);
			}		
		}
		
		ArrayList<ArrayList<Integer>> scheduledata = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<listthesis.length; i++){
			ArrayList<Integer> datum = new ArrayList<Integer>();			
			datum.add(hostProfessorIndex.get(listsupervisor[i]));
			
			//System.out.print(listexaminer1[i]+"examiner1["+i+"]=");
			if(!listexaminer1[i].equals("null")){
				datum.add(guestProfessorIndex.get(listexaminer1[i]));
				//System.out.println(guestProfessorIndex.get(listexaminer1[i]));
			}else{
				datum.add(-1);
				//System.out.println("-1");
			}
			
			//System.out.print(listexaminer2[i]+"examiner2["+i+"]=");			
			if(!listexaminer2[i].equals("null")){
				datum.add(hostProfessorIndex.get(listexaminer2[i]));
				//System.out.println(hostProfessorIndex.get(listexaminer2[i]));
			}else{
				datum.add(-1);
				//System.out.println("-1");
			}
			
			if(!listpresident[i].equals("null")){
				datum.add(hostProfessorIndex.get(listpresident[i]));
			}else{
				datum.add(-1);
			}
			
			if(!listsecretary[i].equals("null")){
				datum.add(hostProfessorIndex.get(listsecretary[i]));
			}else{
				datum.add(-1);
			}
			
			if(!listcommissioner[i].equals("null")){
				datum.add(guestProfessorIndex.get(listcommissioner[i]));
			}else{
				datum.add(-1);
			}
			
			if(!listslot[i].equals("null")){
				datum.add(slotIndex.get(listslot[i]));
			}else{
				datum.add(-1);
			}
			
			if(!listroom[i].equals("null")){
				datum.add(roomIndex.get(listroom[i]));
			}else{
				datum.add(-1);
			}
			scheduledata.add(datum);			
		}
		
		Schedule sc = new Schedule(scheduledata, listAllJuryMembers.size(), listAllJuryExternalMembers.size(), listAllJurySlot.size(), listAllJuryRooms.size(), hostProfessorScore, guestProfessorScore);
		sc.stateModel();
		sc.search();
		
		ArrayList<ArrayList<Integer>> result = sc.getResult();
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		for(int i=0; i<listthesis.length; i++){
			ArrayList<String> committee = new ArrayList<String>();
			ArrayList<Integer> e = result.get(i);
						
			//examiner1
			committee.add(guestProfessors.get(e.get(0)).getEXTSTAF_Code());
			committee.add(guestProfessors.get(e.get(0)).getEXTSTAF_Name());
			
			//examiner2
			committee.add(hostProfessors.get(e.get(1)).getStaff_Code());
			committee.add(hostProfessors.get(e.get(1)).getStaff_Name());
			
			//president
			committee.add(hostProfessors.get(e.get(2)).getStaff_Code());
			committee.add(hostProfessors.get(e.get(2)).getStaff_Name());
			
			//secretary
			committee.add(hostProfessors.get(e.get(3)).getStaff_Code());
			committee.add(hostProfessors.get(e.get(3)).getStaff_Name());
			
			//commissioner
			committee.add(guestProfessors.get(e.get(4)).getEXTSTAF_Code());
			committee.add(guestProfessors.get(e.get(4)).getEXTSTAF_Name());
			
			//slot
			committee.add(listAllJurySlot.get(e.get(5)).getJurySlot_Code());
			committee.add(listAllJurySlot.get(e.get(5)).getJurySlot_Name());
			
			//room
			committee.add(listAllJuryRooms.get(e.get(6)).getJuryRoom_Code());
			committee.add(listAllJuryRooms.get(e.get(6)).getJuryRoom_Code());
						
			data.add(committee);					
		} 	   
 	   
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsondata = gson.toJson(data);
		return jsondata;
		
	}
   	
   	@ResponseBody
	@RequestMapping(value = "/reloadSchedule", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String reloadSchedule(@RequestParam(value = "defensesession") String defensesession,								   
	   							   HttpSession session) {
   	
   	   String userCode = session.getAttribute("currentUserCode").toString(); 
   	   ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
   	   List<mmMasterDefenseJuryThesis> listMasterDefenseJuryThesis = mmmasterDefenseJuryService.listMasterDefenseJuryThesisByDefenseSession(defensesession, userCode);
	   for(mmMasterDefenseJuryThesis masterJuryDefenseJuryThesis:listMasterDefenseJuryThesis){
		   ArrayList<String> datum = new ArrayList<String>();
		   
		   mmExternalStaff examiner1 = masterJuryDefenseJuryThesis.getExaminer1();
		   mmStaff examiner2 = masterJuryDefenseJuryThesis.getExaminer2();
		   mmStaff president = masterJuryDefenseJuryThesis.getPresident();
		   mmStaff secretary = masterJuryDefenseJuryThesis.getSecretary();
		   mmExternalStaff commissioner = masterJuryDefenseJuryThesis.getMember();
		   mmJurySlot slot = masterJuryDefenseJuryThesis.getSlot();
		   mmRooms room = masterJuryDefenseJuryThesis.getRoom();
		   
		   //datum[0]:examiner1-Code
		   //datum[1]:examiner1-Name
		   if(examiner1 == null){
			   datum.add(0, "");
			   datum.add(1, "Chưa xếp");
		   }else{
			   datum.add(0, examiner1.getEXTSTAF_Code());
			   datum.add(1, examiner1.academicRank.getAcademicRank_VNAbbr()+" "+examiner1.getEXTSTAF_Name());
		   }
		   
		   //datum[2]:examiner2-Code
		   //datum[3]:examiner2-Name
		   if(examiner2 == null){
			   datum.add(2, "");
			   datum.add(3, "Chưa xếp");
		   }else{
			   datum.add(2, examiner2.getStaff_Code());
			   datum.add(3, examiner2.academicRank.getAcademicRank_VNAbbr()+" "+examiner2.getStaff_Name());
		   }
		   
		   //datum[4]:president-Code
		   //datum[5]:president-Name
		   if(president == null){
			   datum.add(4, "");
			   datum.add(5, "Chưa xếp");
		   }else{
			   datum.add(4, president.getStaff_Code());
			   datum.add(5, president.academicRank.getAcademicRank_VNAbbr()+" "+president.getStaff_Name());
		   }
		   
		   //datum[6]:secretary-Code
		   //datum[7]:secretary-Name
		   if(secretary == null){
			   datum.add(6, "");
			   datum.add(7, "Chưa xếp");
		   }else{
			   datum.add(6, secretary.getStaff_Code());
			   datum.add(7, secretary.academicRank.getAcademicRank_VNAbbr()+" "+secretary.getStaff_Name());
		   }
		   
		   //datum[8]:commissioner-Code
		   //datum[9]:commissioner-Name
		   if(commissioner == null){
			   datum.add(8, "");
			   datum.add(9, "Chưa xếp");
		   }else{
			   datum.add(8, commissioner.getEXTSTAF_Code());
			   datum.add(9, commissioner.academicRank.getAcademicRank_VNAbbr()+" "+commissioner.getEXTSTAF_Name());
		   }
		   
		   //datum[10]:slot-Code
		   //datum[11]:slot-Name
		   if(slot == null){
			   datum.add(10, "");
			   datum.add(11, "Chưa xếp");
		   }else{
			   datum.add(10, slot.getJurySlot_Code());
			   datum.add(11, slot.getJurySlot_Name());
		   }
		   
		   //datum[10]:slot-Code
		   //datum[11]:slot-Name
		   if(room == null){
			   datum.add(12, "");
			   datum.add(13, "Chưa xếp");
		   }else{
			   datum.add(12, room.getR_Code());
			   datum.add(13, room.getR_Code());
		   }		   
		   data.add(datum);		   
	   }   	   
	   Gson gson = new GsonBuilder().setPrettyPrinting().create();
	   String jsondata = gson.toJson(data);
   	   return jsondata;
	}
   
  
   /**
    * 
    * @param model
    * @param masterThesisCode
    * @param session
    * 
    * @return
    */
   /*@RequestMapping(value = "/remove-a-masterthesis/{code}", method = RequestMethod.GET)
   public String removeAMasterThesis(ModelMap model, @PathVariable("code") String masterThesisCode, HttpSession session) {
 	   String userCode = session.getAttribute("currentUserCode").toString();
 	   model.put("scheduling", mmScheduleController.scheduling);
	   model.put("disableHeader", mmScheduleController.disableHeader);
 	   int isRemoved = mmmasterDefenseJuryService.removeAMasterThesis(userCode, masterThesisCode);
 	   if(isRemoved > 0 ){
 		   List<mmDefenseSession> listDefenseSession  = mmmasterThesisService.listDefenseSession();
 		   List<mmListMasterThesis> listMasterThesis= mmmasterThesisService.listMasterThesis();
 		   List<mmShowedViewMasterDefenseThesis> listMasterDefenseJuryThesis = mmmasterDefenseJuryService.getListMasterDefenseJuryThesisByOwner(userCode);
 		   
 		   model.put("listMasterDefenseJuryThesis", listMasterDefenseJuryThesis);
 		   model.put("listMasterThesis", listMasterThesis);
 		   model.put("listDefenseSession", listDefenseSession);
 		   
 		   return "mm.scheduling";
 	   }
 	  return "mm.scheduling";
   }
   
   /**
    * 
    * @param model
    * @param masterThesisCode
    * @param session
    * 
    * @return
    */
   @RequestMapping(value = "/remove-a-jury-member/{code}", method = RequestMethod.GET)
   public String removeAJuryMember(ModelMap model, @PathVariable("code") String sJuryMemberCode, HttpSession session) {
 	   String userCode = session.getAttribute("currentUserCode").toString();
 	   int isRemoved = mmjuryMemberService.removeAJuryMember(userCode, sJuryMemberCode);
 	   if(isRemoved > 0 ){
 		   List<mmDefenseSession> listDefenseSessions = mmmasterThesisService.listDefenseSession();
 		  	List<mmStaff> listMembers = mmstaffService.listStaffs();
 		  	List<mmJuryMember> listJuryMembers = mmjuryMemberService.listJuryMembers(userCode);
 		  	
 		  	model.put("listDefenseSessions", listDefenseSessions);
 		  	model.put("listMembers", listMembers);
 		  	model.put("listJuryMembers", listJuryMembers);
 		   
 		  return "mm.juryMembers";
 	   }
 	  return "mm.juryMembers";
   }
   
   /**
    * 
    * @param model
    * @param masterThesisCode
    * @param session
    * 
    * @return
    */
   @RequestMapping(value = "/remove-a-jury-room/{code}", method = RequestMethod.GET)
   public String removeAJuryRoom(ModelMap model, @PathVariable("code") String sJuryRoomCode, HttpSession session) {
 	    String userCode = session.getAttribute("currentUserCode").toString();
 	    List<mmDefenseSession> listDefenseSessions = mmmasterThesisService.listDefenseSession();
	   	List<mmRooms> listRooms = mmroomsService.listRooms();
	   	
	   	model.put("listDefenseSessions", listDefenseSessions);
	   	model.put("listRooms", listRooms);
 	   int isRemoved = mmjuryRoomService.removeAJuryRoom(userCode, sJuryRoomCode);
 	   if(isRemoved > 0 ){
 			List<mmJuryRoom> listJuryRooms = mmjuryRoomService.listJuryRooms(userCode);
 			model.put("listJuryRooms", listJuryRooms);
 		    return "mm.juryRooms";
 	   }
 	  return "mm.juryRooms";
   }
   
   /**
    * 
    * @param model
    * @param masterThesisCode
    * @param session
    * 
    * @return
    */
   @RequestMapping(value = "/remove-a-jury-slot/{code}", method = RequestMethod.GET)
   public String removeAJurySlot(ModelMap model, @PathVariable("code") String sJurySlotCode, HttpSession session) {
 	   String userCode = session.getAttribute("currentUserCode").toString();
 	   List<mmDefenseSession> listDefenseSessions = mmmasterThesisService.listDefenseSession();
   	
	   model.put("listDefenseSessions", listDefenseSessions);
 	   int isRemoved = mmjurySlotService.removeAJurySlot(userCode, sJurySlotCode);
 	   if(isRemoved > 0 ){
 		  List<mmJurySlot> listJurySlots = mmjurySlotService.listJurySlots(userCode);
 		  model.put("listJurySlots", listJurySlots);
 		  return "mm.jurySlots";
 	   }
 	  return "mm.jurySlots";
   }
   
   @RequestMapping(value = "/viewPdf/{id}", method = RequestMethod.GET)
   public void viewPdf(HttpServletRequest req, HttpServletResponse res, ModelMap model, @PathVariable("id") int masterDefenseJuryThesis_ID, HttpSession session) throws IOException {

		mmMasterDefenseJuryThesis masterDefenseJuryThesis = mmmasterDefenseJuryService.getMasterDefenseJuryThesisById(masterDefenseJuryThesis_ID);

		final ServletContext servletContext = session.getServletContext();
		final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		final String temporaryFilePath = tempDirectory.getAbsolutePath();

		String fileName = masterDefenseJuryThesis.getMASDEFJury_Code() + ".pdf";
		
		String path = temporaryFilePath + "\\" + fileName;

		File file = new File(path);
		FileInputStream inputStream = new FileInputStream(file);

		res.setContentType("application/pdf");
		res.setContentLength((int) file.length());
		res.setHeader("Content-Disposition", "inline;filename=\"" + fileName+ "\"");

		FileCopyUtils.copy(inputStream, res.getOutputStream());
   		   
   }
   
}
