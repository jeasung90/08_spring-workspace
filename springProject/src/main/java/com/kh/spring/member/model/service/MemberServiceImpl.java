package com.kh.spring.member.model.service;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

//@Component 
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao mDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession ;
	
	@Override
	public Member loginMember(Member m) {
		
		
		Member loginMember = mDao.loginMember(sqlSession, m);
		
		return loginMember;
	}

	@Override
	public int insertMember(Member m) {
		
		int result = mDao.insertMember(sqlSession, m);
		return result;
	}

	@Override
	public int updateMember(Member m) {
		
		int result = mDao.updateMember(sqlSession,m);
		return result;
	}

	@Override
	public int deleteMember(String userId) {
		int result = mDao.deleteMember(sqlSession,userId);
		return result;
	}

	@Override
	public int idCheck(String checkId) {
		int count = mDao.idCheck(sqlSession,checkId);
		return count;
	}
	

}
