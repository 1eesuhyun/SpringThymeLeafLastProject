package com.sist.web.service;
import org.springframework.stereotype.Service;
import com.sist.web.mapper.SeoulMapper;
import lombok.RequiredArgsConstructor;
import java.util.*;
import com.sist.web.vo.*;
@Service
@RequiredArgsConstructor
/*
 *  @Autorired
 *  public SeoulServiceImpl(SeoulMapper mapper)
 *  {
 *  	this.mapper=mapper;
 *  }
 */
public class SeoulServiceImpl implements SeoulService{
	private final SeoulMapper mapper;

	@Override
	public List<SeoulVO> seoulMainData(Map map) {
		// TODO Auto-generated method stub
		return mapper.seoulMainData(map);
	}
}
