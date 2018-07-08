package com.company.project.service.impl;

import com.company.project.dao.TFileImagesMapper;
import com.company.project.dao.TFileMapper;
import com.company.project.model.TFile;
import com.company.project.model.TFileImages;
import com.company.project.service.TFileService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/06/20.
 */
@Service
@Transactional
public class TFileServiceImpl extends AbstractService<TFile> implements TFileService {
    @Resource
    private TFileMapper tFileMapper;

    @Resource
    private TFileImagesMapper tFileImagesMapper;



    @Override
    public List<TFile> findByhaxi(String haxi) {
        return  tFileMapper.findByhaxi(haxi);
    }


    @Override
    public Integer deleteTFileById(Integer id) {

        return  tFileMapper.deleteTFileById(id);
    }

    @Override
    public boolean setDocument(TFile worddata, List<TFileImages> wordimglist) {
        int insert = tFileMapper.insertTfile(worddata);

        for(int i=0;i<wordimglist.size();i++){
            wordimglist.get(i).setWordId(worddata.getId());
            wordimglist.get(i).setName(worddata.getName());
        }

        int insertList = tFileImagesMapper.insertListTfiles(wordimglist);

        if(insert>0){
            return true;
        }
        return false;
    }
}
