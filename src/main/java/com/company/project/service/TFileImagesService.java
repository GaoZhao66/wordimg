package com.company.project.service;
import com.company.project.model.TFileImages;
import com.company.project.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2018/06/20.
 */
public interface TFileImagesService extends Service<TFileImages> {

    List<String> findByWordId(Integer id);
}
