package com.model2.mvc.web.notice;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Notice;
import com.model2.mvc.service.notice.NoticeDao;
import com.model2.mvc.service.notice.NoticeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/notice/*")
@CrossOrigin(origins = "http://localhost:3000")
public class NoticeRestController {

	//Field
	@Autowired
	@Qualifier("noticeServiceImpl")
	private NoticeService noticeService;
	
	public NoticeRestController() {
		System.out.println(this.getClass());
	}

	@Value("${pageUnit}")
	int pageUnit;

	@Value("${pageSize}")
	int pageSize;

		@RequestMapping(value = "/json/noticelist")
		public Map<String,Object> notice_list(@ModelAttribute("Search") Search search)throws Exception
		{
			System.out.println("listnotice json Start.....");
			if(search.getCurrentPage() == 0 ) {
				search.setCurrentPage(1);
			}
			
			search.setPageSize(pageSize);
			
			Map<String,Object> noticeMap = noticeService.getNoticeList(search);
			
			Page resultPage = new Page(search.getCurrentPage(),
					((Integer)noticeMap.get("totalCount"))
					.intValue(),pageUnit,pageSize);
			System.out.println(resultPage);
			
			noticeMap.put("list", noticeMap.get("list"));
			noticeMap.put("resultPage", resultPage);
			noticeMap.put("search", search);
			
			return noticeMap;
		}
		
		@GetMapping(value = "/json/getNotice/{noticeNo}")
		public Notice getNotice(@PathVariable int noticeNo) throws Exception {
			System.out.println("getNotice json start......");

			Notice noti = noticeService.getNotice(noticeNo);

			return noti;
		}
		
		@PostMapping("/json/addNotice")
	    public ResponseEntity<String> addNotice(@RequestBody Notice notice) {
	        try {
	            noticeService.addNotice(notice); // 서비스를 통해 입력된 값을 저장
	            return new ResponseEntity<>("Notice added successfully", HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Error adding notice: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
		
		@PostMapping(value = "/json/updateNotice")
		public Notice updateNotice(@ModelAttribute("notice") Notice notice) throws Exception{
			
			System.out.println("updateProduct json start......");
			noticeService.updateNotice(notice);
			
			return notice;
		}
		
		@GetMapping(value = "/json/updateNotice/{noticeNo}")
		public Notice updateNotice(@PathVariable int noticeNo) throws Exception{
			
			System.out.println("updateProduct json start......");
			Notice notice = noticeService.getNotice(noticeNo);
			
			return notice;
		}
		
		@GetMapping(value = "/json/delete/{noticeNo}")
		public Notice deleteNotice(@PathVariable int noticeNo) throws Exception {
			System.out.println("deleteNotice json start......");
			Notice notice = noticeService.delNotice(noticeNo);
		
			return notice;
		}
		
}