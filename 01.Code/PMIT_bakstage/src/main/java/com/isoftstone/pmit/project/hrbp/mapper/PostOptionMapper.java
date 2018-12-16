package com.isoftstone.pmit.project.hrbp.mapper;

import com.isoftstone.pmit.project.hrbp.entity.PostOptionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.List;

@Repository
@Mapper
public interface PostOptionMapper {
    PostOptionInfo queryPostOption(Map<String, Object> params);

    List<PostOptionInfo> queryPostOptionInfos(Map<String, Object> params);

    int addPostOption(PostOptionInfo newPost);

    int updatePostOption(PostOptionInfo onePost);

    int deletePostOption(int postId);
}
