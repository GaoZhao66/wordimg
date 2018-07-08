package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.TFileImages;

import java.util.List;

public interface TFileImagesMapper extends Mapper<TFileImages> {

    /**
     * 通过wordid查询图片
     * @param wordId
     * @return
     */
    List<TFileImages> findByWordId(Integer wordId);

    int insertListTfiles(List<TFileImages> wordimglist);
}