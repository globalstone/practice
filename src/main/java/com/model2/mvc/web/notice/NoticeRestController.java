package com.model2.mvc.web.notice;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Notice;
import com.model2.mvc.service.notice.NoticeDao;
import com.model2.mvc.service.notice.NoticeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/notice/*")
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

		@RequestMapping(value = "json/noticelist")
		public Map<String,Object> notice_list(@ModelAttribute("Search") Search search)throws Exception
		{
			System.out.println("/listnotice Start.....");
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
			System.out.println("/getNotice");

			Notice noti = noticeService.getNotice(noticeNo);

			return noti;
		}
}