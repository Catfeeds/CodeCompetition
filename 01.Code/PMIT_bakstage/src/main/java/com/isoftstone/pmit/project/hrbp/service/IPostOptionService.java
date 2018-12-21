package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.PostOptionInfo;
import com.isoftstone.pmit.project.hrbp.entity.RelationTreeNode;
import java.util.List;
import java.util.Map;

public interface IPostOptionService {
    /**
     * 查询所有岗位项信息
     * @param params 查询参数
     * @return 岗位项列表
     */
    List<PostOptionInfo> queryPostOptionInfos(Map<String, Object> params);

    /**
     * 新增或修改单个岗位选项
     * @param onePost 新增内容，postId为-1时，为新增
     * @return 新增或修改结果状态
     */
    boolean savePostOption (PostOptionInfo onePost);

    /**
     * 删除选中岗位选项
     * @param postId
     * @return 删除结果状态
     */
    boolean deletePostOption(int postId);
}
