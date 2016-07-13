/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.controller.cpservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.webapp.controller.BaseRest;
import vn.webapp.dto.Response;
import vn.webapp.modules.mastermanagement.service.mmStaffService;

/**
 *
 * @author Tonytran
 */
@Controller("mmServiceStaff")
@RequestMapping(value = {"/mmservice"})
public class StaffController extends BaseRest {

    @Autowired
    private mmStaffService staffService;
    

    @ResponseBody
    @RequestMapping(value = "editthestaff", method = RequestMethod.POST)
    public Response delCoursing(@RequestParam(value = "id", defaultValue = "0") int id) {
    	//TODO
        int res = 1;
        if (res > 0) {
            return new Response(true, "Add successfully.", res);
        } else {
            return new Response(false, "Add unsuccessfully. Please try again.", res);
        }
    }

}
