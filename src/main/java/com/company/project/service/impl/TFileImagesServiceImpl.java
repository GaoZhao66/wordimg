package com.company.project.service.impl;

import com.company.project.dao.TFileImagesMapper;
import com.company.project.model.TFileImages;
import com.company.project.service.TFileImagesService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * Created by CodeGenerator on 2018/06/20.
 */
@Service
@Transactional
public class TFileImagesServiceImpl extends AbstractService<TFileImages> implements TFileImagesService {
    @Resource
    private TFileImagesMapper tFileImagesMapper;

    @Override
    public List<String> findByWordId(Integer wordId) {
        List<TFileImages> tFileImages = tFileImagesMapper.findByWordId(wordId);

        List<String> imgsList=new ArrayList<>();


        for (TFileImages tf  : tFileImages) {
            imgsList.add(tf.getAddress());
        }

        return imgsList;
    }
}
