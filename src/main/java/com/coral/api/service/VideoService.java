package com.coral.api.service;

import com.coral.api.dao.VideoMongoDao;
import com.coral.api.model.VideoVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccc on 2018/4/24.
 */
@Service
public class VideoService {

    public static final String ADMIN_PASSWORD="CCCIS123";
    @Resource(name=VideoMongoDao.SPRING_BEAN_NAME)
    VideoMongoDao dao;

    public List<VideoVO> getAll() {
        return dao.findAll();
    }

    public VideoVO saveVideo(VideoVO videoVO) {
        dao.save(videoVO);
        return videoVO;
    }

    public void delete(VideoVO videoVO) {
        dao.remove(videoVO);
    }

    public String validate(String pwd) {
        if(ADMIN_PASSWORD.equals(pwd)) {
            return "1";
        }
        return "0";
    }

}
