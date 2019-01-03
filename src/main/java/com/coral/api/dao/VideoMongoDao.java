package com.coral.api.dao;

import com.coral.base.mongo.MBaseDao;
import com.coral.api.model.VideoVO;
import org.springframework.stereotype.Repository;

/**
 * Created by ccc on 2018/4/24.
 */
@Repository(VideoMongoDao.SPRING_BEAN_NAME)
public class VideoMongoDao extends MBaseDao<VideoVO> {

    public final static String SPRING_BEAN_NAME = "mongo.VideoMongoDao";

    @Override
    public Class getEntityClass() {
        return VideoVO.class;
    }
}