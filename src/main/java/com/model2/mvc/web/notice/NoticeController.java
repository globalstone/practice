package com.model2.mvc.web.notice;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Notice;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.notice.NoticeDao;
import com.model2.mvc.service.notice.NoticeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/notice/*")
public class NoticeController {

	//Field
	@Autowired
	@Qualifier("noticeServiceImpl")
	private NoticeService noticeService;
	
	public NoticeController() {
		System.out.println(this.getClass());
	}

	@Value("${pageUnit}")
	int pageUnit;

	@Value("${pageSize}")
	int pageSize;

		@RequestMapping("/list")
		public String notice_list(@ModelAttribute("Search") Search search,
										Model model)throws Exception
		{
			System.out.println("/listnotice Start.....");
			if(search.getCurrentPage() == 0 ) {
				search.setCurrentPage(1);
			}
			
			search.setPageSize(pageSize);
			
			Map<String,Object> map = noticeService.getNoticeList(search);
			
			Page resultPage = new Page(search.getCurrentPage(),
					((Integer)map.get("totalCount"))
					.intValue(),pageUnit,pageSize);
			System.out.println(resultPage);
			
			model.addAttribute("list",map.get("list"));
			model.addAttribute("resultPage",resultPage);
			model.addAttribute("search",search);
			return "forward:/notice/noticeList.jsp";
		}
		
		@PostMapping(value = "addNotice")
		public String add_Notice(@ModelAttribute("Notice") Notice notice, 
                HttpSession session) throws Exception {
					System.out.println("addNotice.Post");

					String name = (String) session.getAttribute("name");
					System.out.println("name=" + name);
					
					noticeService.addNotice(notice);

					return "redirect:/notice/list";
		}
		
		@GetMapping(value = "addNotice")
		public String addProduct() throws Exception{
			System.out.println("addNotice.GET");
			return "forward:/notice/addNotice.jsp";
		}
		
		@GetMapping(value = "getNotice/{noticeNo}")
		public String getNotice(@PathVariable int noticeNo,
								 Model model) throws Exception {
			System.out.println("/getNotice");

			Notice notice = noticeService.getNotice(noticeNo);
			model.addAttribute("noti", notice);
			System.out.println("notice == " + notice);

			return "forward:/notice/getNotice.jsp";
		}
		
		@PostMapping(value = "updateNotice")
		public String updateNotice(@ModelAttribute("update") Notice notice) throws Exception{
			
			System.out.println("/updateProduct.do");
			noticeService.updateNotice(notice);
			
			return "forward:/notice/updateReadNotice.jsp";
		}
		
		@GetMapping(value = "updateNotice/{noticeNo}")
		public String updateNotice(@PathVariable int noticeNo,
									Model model) throws Exception{
			
			System.out.println("/updateProductView.do");
			Notice notice = noticeService.getNotice(noticeNo);
			model.addAttribute("UpdateNotice", notice);
			
			return "forward:/notice/updateNotice.jsp";
		}
		
		@GetMapping(value = "delete/{noticeNo}")
		public String deleteNotice(@PathVariable int noticeNo,
									Model model) throws Exception {
			System.out.println("deleteNotice start......");
			Notice notice = noticeService.delNotice(noticeNo);
		
			return "redirect:/notice/list";
		}
}