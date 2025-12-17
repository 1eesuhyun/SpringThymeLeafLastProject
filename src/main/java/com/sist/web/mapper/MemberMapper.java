package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.MemberVO;

@Repository
@Mapper
public interface MemberMapper {
	@Select("SELECT COUNT(*) FROM member_1 "
			+ "WHERE id=#{id}")
	public int memberIdCount(String id);
	
	@Select("SELECT id,name,sex,pwd,address FROM member_1 "
			+ "WHERE id=#{id}")
	public MemberVO memberInfoData(String id);
}
