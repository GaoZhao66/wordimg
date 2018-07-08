package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.TFile;

import java.util.List;

public interface TFileMapper extends Mapper<TFile> {


    List<TFile> findByhaxi(String haxi);

    Integer deleteTFileById(Integer id);

    int insertTfile(TFile worddata);
}