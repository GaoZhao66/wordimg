package com.company.project.service;

import java.util.List;

import com.company.project.model.WordFile;
import org.springframework.stereotype.Service;

/**
 * 文档转图片的接口
 * @author Administrator
 *
 */
@Service
public interface WordService {
	List<String> getWordImgList(WordFile wordfile);
}
