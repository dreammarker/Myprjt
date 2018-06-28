package com.yedam.web.emp.view;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.web.emp.EmpService;
import com.yedam.web.emp.EmpVO;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 * 
 * @author user
 *
 */
@Controller
public class EmpController {

	@Autowired
	EmpService empService;

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

	@RequestMapping(value = "/insertEmp", method = RequestMethod.POST)
	public String insertEmp(HttpServletRequest request, EmpVO vo) {
		System.out.println(vo);
		return "redirect:/getEmpList";
	}

	// 차트페이지
	@RequestMapping("emp/chart")
	public void insertEmp() {

	}

	// 차트 데이터 ajax
	@RequestMapping("emp/getDeptCnt")
	@ResponseBody
	public List<Map<String, Object>> getDeptCnt() {
		return empService.getDeptCnt();
	}

	@RequestMapping("report.do")
	public void report(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			JasperReport report = JasperCompileManager
					.compileReport(request.getSession().getServletContext().getRealPath("reports/empReport2.jrxml"));
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(empService.getEmpList());
			JasperPrint print = JasperFillManager.fillReport(report, map, JRdataSource);
			JRExporter exporter = new JRPdfExporter();
			OutputStream out;
			response.reset();
			out = response.getOutputStream();
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "report3.pdf");
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			exporter.exportReport();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
