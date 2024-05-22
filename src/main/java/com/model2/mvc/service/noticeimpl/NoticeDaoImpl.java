package com.model2.mvc.service.noticeimpl;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Notice;
import com.model2.mvc.service.notice.NoticeDao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("noticeDaoImpl")
public class NoticeDaoImpl implements NoticeDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public NoticeDaoImpl() {
		System.out.println("NoticeDaoImpl start.....");
	}

	@Override
	public void insertNotice(Notice notice) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("NoticeMapper.insertNotice",notice);
	}

	@Override
	public Notice detailNotice(int noticeNo) throws Exception {
		// TODO Auto-generated method stub
		return (Notice)sqlSession.selectOne("NoticeMapper.detailNotice",noticeNo);
	}

	@Override
	public void updateNotice(Notice notice) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("NoticeMapper.updateNotice",notice);
	}

	@Override
	public List<Notice> getNoticeList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("NoticeMapper.getNoticeList",search);
	}
	
	@Override
	public String getPwdNotice(int noticeNo,String noticePwd) throws Exception {
		 String result="";
		 SqlSession session=null;
		 try
		 {
			 String db_pwd=session.selectOne("getPwdNotice",noticeNo);
			 if(db_pwd.equals(noticePwd))
			 {
				 result="yes";
			 }
			 else
			 {
				 result="no";
			 }
		 }catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
		 finally
		 {
			 if(session!=null)
				 session.close();
		 }
		 return result;
	}
	
	@Override
	public Notice deleteNotice(int noticeNo) throws Exception {
		return (Notice)sqlSession.selectOne("NoticeMapper.deleteNotice",noticeNo);
	}
	
	@Override
	public int getTotalCount(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("ProductMapper.getTotalCount",search);
	}
	
}
