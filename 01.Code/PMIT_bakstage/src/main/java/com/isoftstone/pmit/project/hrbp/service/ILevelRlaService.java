package com.isoftstone.pmit.project.hrbp.service;

import com.isoftstone.pmit.project.hrbp.entity.LevelTreeNode;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface ILevelRlaService {
    public List<LevelTreeNode> queryLevelRlaNode(List<Map<String, Object>> nodeList) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    public void addLevelRlaNode(Map<String,Object> queryMap);

    public void deleteLevelRlaNode(String nodePath,Integer nodeID);

    public void moveLevelRlaNode(Map<String,Object> queryMap);

    public void updateLevelRlaNode(Map<String,Object> queryMap);
}
