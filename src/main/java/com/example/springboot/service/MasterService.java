package com.example.springboot.service;

import com.example.springboot.dto.MasterRecommendDTO;
import org.apache.mahout.cf.taste.common.TasteException;

import java.io.IOException;

public interface MasterService {
    MasterRecommendDTO recommendMaster(long mainCategoryId, String answerTime) throws IOException, TasteException;
}
