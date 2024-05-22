package com.model2.mvc.service.noticeimpl;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Notice;
import com.model2.mvc.service.notice.NoticeDao;
import com.model2.mvc.service.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("noticeServiceImpl")
public class NoticeServiceimpl implements NoticeService{
	
	@Autowired
	@Qualifier("noticeDaoImpl")
	private NoticeDao noticeDao;
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	public NoticeServiceimpl() {
		System.out.println(this.getClass());
	}
	
	public void addNotice(Notice notice) throws Exception{
		noticeDao.insertNotice(notice);
	}
	
	public Notice getNotice(int noticeNo) throws Exception{
		return noticeDao.detailNotice(noticeNo);
	}
	
	public Map<String, Object> getNoticeList(Search search) throws Exception{
		List<Notice> list = noticeDao.getNoticeList(search);
		int totalCount = noticeDao.getTotalCount(search);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		return map;
	}
	
	public void updateNotice(Notice notice) throws Exception{
		noticeDao.updateNotice(notice);
	}
	//===================

	@Override
	public Notice delNotice(int noticeNo) throws Exception {
		return noticeDao.deleteNotice(noticeNo);
	}
	
	@Override
	public String getPwd(int noticeNo,String noticePwd) throws Exception {
		return noticeDao.getPwdNotice(noticeNo,noticePwd);
	}
}
