package com.yedam.web.board.view;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.yedam.web.Paging;
import com.yedam.web.board.BoardSearchVO;
import com.yedam.web.board.BoardService;
import com.yedam.web.board.BoardVO;

@Controller
@SessionAttributes("board") // 모델에 board 속성이 추가된다면 session에도 추가
public class BoardController {

	@Autowired
	BoardService boardService;

	@ModelAttribute("conditionMap")
	public Map<String, String> SearchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}

	@RequestMapping("/getBoardList")
	public ModelAndView getBoardList(BoardSearchVO vo,Paging paging) {
		ModelAndView mv = new ModelAndView();
		//페이지 번호 파라미터
		if(paging.getPage()==null)
			paging.setPage(1);
		//페이징 first,last 검색조건
		//page 1 ==> 1~10 2=> 11~20
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		//전체건수
		paging.setTotalRecord(boardService.getCount(vo));
		
		//결과를 모델에 저장
		mv.addObject("paging", paging);
		mv.addObject("boardList", boardService.getBoardList(vo));
		
		//뷰페이지 지정
		mv.setViewName("board/getBoardList");
		return mv;
	}

	// 등록폼
	@RequestMapping(value = "/insertBoard", method = RequestMethod.GET)
	public String insertBoardForm() {
		return "board/insertBoard";
	}

	// 수정폼
	@RequestMapping(value = "/updateBoard", method = RequestMethod.GET)
	public String updateBoardForm() {
		// 단건조회
		return "board/updateBoard";
	}

	// 수정처리
	@RequestMapping(value = "/updateBoard", method = RequestMethod.POST)
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		System.out.println(vo);
		boardService.updateBoard(vo);
		return "board/updateBoard";
	}

	// 등록처리
	@RequestMapping(value = "/insertBoard", method = RequestMethod.POST)
	public String insertBoard(@ModelAttribute("vo") BoardVO vo, HttpServletRequest request)
			throws IllegalStateException, IOException {
		// model.addAttribute("boardVO",vo);
		// 첨부 파일 처리 (서버 업로드 위치로 파일 업로하고 파일명 추출)

		// MultipartFile file =vo.getTempuploadfile();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("tempuploadfile");

		if (!file.isEmpty() && file.getSize() > 0) {
			String filename = file.getOriginalFilename();
			file.transferTo(new File("d:/upload", filename));
			vo.setUploadfile(filename);
		}
		// 서비스이용하여 등록처리하고 목록페이지로 이동
		boardService.insertBoard(vo);
		return "board/getBoard";
	}

	/*
	 * @RequestMapping("/getBoard") public String
	 * getBoard(@RequestParam(value="s",required=false, defaultValue="1") String
	 * seq,Model model) { System.out.println("seq: "+seq);
	 * model.addAttribute("board",boardService.getBoard(seq)); return
	 * "board/getBoard"; }
	 */
	@RequestMapping("/getBoard/{seq}")
	public String getBoard(@PathVariable String seq, Model model) {
		System.out.println("seq: " + seq);
		model.addAttribute("vo", boardService.getBoard(seq));
		return "board/getBoard";
	}

	@RequestMapping("/downloadBoard")
	public void downloadBoard(@RequestParam String seq, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(seq+"========================");
		BoardVO vo = boardService.getBoard(seq);
		String filename = vo.getUploadfile();
		File uFile = new File("d:/upload", filename);
		long fSize = uFile.length();
		if (fSize > 0) {
			String mimetype = "application/x-msdownload";
			response.setContentType(mimetype);
			setDisposition(filename, request, response);
			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				out = new BufferedOutputStream(response.getOutputStream());
//				FileCopyUtils.copy(in, out);
				int temp;
				while((temp=in.read())!=-1) {
					out.write(temp);
				}
				
					out.flush();
			} catch (IOException ex) {
			} finally {
				in.close();
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		} else {
			response.setContentType("application/x-msdownload");
			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<h2>Could not get file name:<br>" + filename + "</h2>");
			printwriter.println("<center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("&copy; webAccess");
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();
		}
	}

	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}

	private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String browser = getBrowser(request);
		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;
		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}
		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);
		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}
}
