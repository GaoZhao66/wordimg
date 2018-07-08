package com.company.project.service;
import com.company.project.model.TFile;
import com.company.project.core.Service;
import com.company.project.model.TFileImages;

import java.util.List;


/**
 * Created by CodeGenerator on 2018/06/20.
 */
public interface TFileService extends Service<TFile> {

    List<TFile> findByhaxi(String haxi);

    Integer deleteTFileById(Integer id);

    boolean setDocument(TFile worddata, List<TFileImages> wordimglist);
}
