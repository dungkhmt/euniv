package vn.webapp.modules.researchdeclarationmanagement.controller.cpservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.webapp.controller.BaseRest;
import vn.webapp.dto.Response;
import vn.webapp.modules.usermanagement.service.mStaffService;

@Controller("cpmServicePaper")
@RequestMapping(value = {"/cpservice"})
public class mPaperController extends BaseRest {
	@Autowired
    private mStaffService staffService;

    @ResponseBody
    @RequestMapping(value = "generate", method = RequestMethod.POST)
    public Response generatePaperXls() {
    	//TODO
        int res = 1;
        if (res > 0) {
            return new Response(true, "Add successfully.", res);
        } else {
            return new Response(false, "Add unsuccessfully. Please try again.", res);
        } 
    }
}
