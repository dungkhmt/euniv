/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vn.webapp.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.webapp.dto.Response;

public class BaseRest {
    
    @ExceptionHandler
    @ResponseBody
    public Response handleExceptions(Exception ex) {
        return new Response(false, ex.getMessage(), ex);
    }
}
