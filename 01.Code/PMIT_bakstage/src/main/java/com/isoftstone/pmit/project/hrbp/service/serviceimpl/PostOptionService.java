package com.isoftstone.pmit.project.hrbp.service.serviceimpl;

import com.isoftstone.pmit.common.constant.CommonConst;
import com.isoftstone.pmit.common.util.StringUtilsMethod;
import com.isoftstone.pmit.project.hrbp.entity.PostOptionInfo;
import com.isoftstone.pmit.project.hrbp.entity.RelationTreeNode;
import com.isoftstone.pmit.project.hrbp.mapper.PostOptionMapper;
import com.isoftstone.pmit.project.hrbp.service.IPostOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PostOptionService implements IPostOptionService {

    @Autowired
    private PostOptionMapper postOptionMapper;

    @Override
    public RelationTreeNode queryBuByPostOption(Map<String, Object> params) {
        PostOptionInfo onePost = postOptionMapper.queryPostOption(params);

        String authCont = onePost.getAuthContent();
        if (StringUtilsMethod.isEmpty(authCont)) {
            return null;
        }

        String[] postInfo = authCont.split(CommonConst.DATA_SEPARATOR);
        // 调用光少的方法拼接树
        return null;
    }

    @Override
    public List<PostOptionInfo> queryPostOptionInfos(Map<String, Object> params) {
        return postOptionMapper.queryPostOptionInfos(params);
    }

    @Override
    public boolean savePostOption(PostOptionInfo onePost) {
        int rst;
        if (CommonConst.INVALID_VALUE == onePost.getPostId()) {
            rst = postOptionMapper.addPostOption(onePost);
        } else {
            rst = postOptionMapper.updatePostOption(onePost);
        }
        return CommonConst.SQL_EXECUTE_FAILED != rst;
    }

    @Override
    public boolean deletePostOption(int postId) {
        return CommonConst.SQL_EXECUTE_FAILED != postOptionMapper.deletePostOption(postId);
    }
}
