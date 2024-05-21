package com.model2.mvc.service.notice;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Notice;

import java.util.Map;

public interface NoticeService {
	
	public void addNotice(Notice Notice) throws Exception;
	
	public Notice getNotice(int noticeNo) throws Exception;
	
	public Map<String, Object> getNoticeList(Search search) throws Exception;
	
	public void updateNotice(Notice notice) throws Exception;
	
	public void delNotice(Notice notice) throws Exception;
	
	public String getPwd(int noticeNo,String noticePwd) throws Exception;
}