package com.example.springboot.service;

import com.example.springboot.dto.MentorRecommendDTO;
import org.apache.mahout.cf.taste.common.TasteException;

import java.io.IOException;

public interface MentorService {
    MentorRecommendDTO recommendMentor(String id, Long mainCategoryId) throws IOException, TasteException;
}
