package com.yedam.web.emp.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yedam.web.emp.EmpService;
import com.yedam.web.emp.EmpVO;

/** 
 * 
 * @author user
 *
 */
@Controller
public class EmpController {

	@Autowired EmpService empService;
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getEmpList")
	public String getEmpList(HttpServletRequest request) {
		request.setAttribute("empList", empService.getEmpList());
		return "emp/getEmpList";
	}
	
	@RequestMapping(value="/insertEmp", method=RequestMethod.POST )
	public String insertEmp(HttpServletRequest request, EmpVO vo) {
		System.out.println(vo);
		return "redirect:/getEmpList";
	}	
	
}
