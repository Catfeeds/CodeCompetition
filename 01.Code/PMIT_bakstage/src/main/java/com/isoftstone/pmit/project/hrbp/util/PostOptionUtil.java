package com.isoftstone.pmit.project.hrbp.util;

import com.isoftstone.pmit.common.util.ListUtils;
import com.isoftstone.pmit.project.hrbp.entity.RelationTreeNode;

import java.util.ArrayList;
import java.util.List;

public class PostOptionUtil {

    public static final String TREE_PATH_SEPARATOR = ":";

    public static void covertPostOption2Path(RelationTreeNode inputSelectOpt, List<String> rst) {
        if (isCantSelectNode(inputSelectOpt)) {
            return;
        }

        List<String> sonSelectRst = new ArrayList<>();
        for (RelationTreeNode oneNode : inputSelectOpt.getChildList()) {
            covertPostOption2Path(oneNode, sonSelectRst);
        }

        if (inputSelectOpt.getIsHasAuthority() && (inputSelectOpt.getChildList().size() == sonSelectRst.size()
                || sonSelectRst.size() == 0)) {
            rst.add(inputSelectOpt.getNodePath() + inputSelectOpt.getNodeID() + TREE_PATH_SEPARATOR);
        } else {
            rst.addAll(sonSelectRst);
        }
    }

    private static boolean isCantSelectNode(RelationTreeNode inputSelectOpt) {
        return !inputSelectOpt.getIsSelected() || inputSelectOpt.getIsTeamNode() ||
                (!inputSelectOpt.getIsHasAuthority() && ListUtils.isEmpty(inputSelectOpt.getChildList()));
    }

    public static void main(String[] args) {
        RelationTreeNode baseNode = new RelationTreeNode();
        baseNode.setNodeID(1);
        baseNode.setNodeName("2012&企业IT");
        baseNode.setNodeType("BD");
        baseNode.setNodePath(":");
        baseNode.setIsTeamNode(false);
        baseNode.setIsHasAuthority(true);
        baseNode.setIsSelected(true);
        baseNode.setChildList(new ArrayList<>());

        List<RelationTreeNode> buNodes = baseNode.getChildList();
        buNodes.add(new RelationTreeNode());
        buNodes.add(new RelationTreeNode());

        RelationTreeNode _2012BuNode = buNodes.get(0);
        _2012BuNode.setNodeID(2);
        _2012BuNode.setNodeName("2012");
        _2012BuNode.setNodeType("BU");
        _2012BuNode.setNodePath(":1:");
        _2012BuNode.setIsTeamNode(false);
        _2012BuNode.setIsHasAuthority(true);
        _2012BuNode.setIsSelected(true);
        _2012BuNode.setChildList(new ArrayList<>());

        RelationTreeNode iTBuNode = buNodes.get(1);
        iTBuNode.setNodeID(3);
        iTBuNode.setNodeName("企业IT");
        iTBuNode.setNodeType("BU");
        iTBuNode.setNodePath(":1:");
        iTBuNode.setIsTeamNode(false);
        iTBuNode.setIsHasAuthority(true);
        iTBuNode.setIsSelected(true);
        iTBuNode.setChildList(new ArrayList<>());

        List<RelationTreeNode> _2012CuNodes = _2012BuNode.getChildList();
        _2012CuNodes.add(new RelationTreeNode());
        _2012CuNodes.add(new RelationTreeNode());
        _2012CuNodes.add(new RelationTreeNode());

        RelationTreeNode xianBuNode = _2012CuNodes.get(0);
        xianBuNode.setNodeID(4);
        xianBuNode.setNodeName("西安交付部");
        xianBuNode.setNodeType("CU");
        xianBuNode.setNodePath(":1:2:");
        xianBuNode.setIsTeamNode(false);
        xianBuNode.setIsHasAuthority(true);
        xianBuNode.setIsSelected(true);
        xianBuNode.setChildList(new ArrayList<>());


        RelationTreeNode chengduBuNode = _2012CuNodes.get(1);
        chengduBuNode.setNodeID(5);
        chengduBuNode.setNodeName("成都交付部");
        chengduBuNode.setNodeType("CU");
        chengduBuNode.setNodePath(":1:2:");
        chengduBuNode.setIsTeamNode(false);
        chengduBuNode.setIsHasAuthority(true);
        chengduBuNode.setIsSelected(true);
        chengduBuNode.setChildList(new ArrayList<>());


        RelationTreeNode changshaBuNode = _2012CuNodes.get(2);
        changshaBuNode.setNodeID(6);
        changshaBuNode.setNodeName("成都交付部");
        changshaBuNode.setNodeType("CU");
        changshaBuNode.setNodePath(":1:2:");
        changshaBuNode.setIsTeamNode(false);
        changshaBuNode.setIsHasAuthority(true);
        changshaBuNode.setIsSelected(true);
        changshaBuNode.setChildList(new ArrayList<>());

        List<RelationTreeNode> itCuNodes = iTBuNode.getChildList();
        itCuNodes.add(new RelationTreeNode());
        itCuNodes.add(new RelationTreeNode());
        itCuNodes.add(new RelationTreeNode());

        RelationTreeNode shenzhenBuNode = itCuNodes.get(0);
        shenzhenBuNode.setNodeID(7);
        shenzhenBuNode.setNodeName("深圳交付部");
        shenzhenBuNode.setNodeType("CU");
        shenzhenBuNode.setNodePath(":1:3:");
        shenzhenBuNode.setIsTeamNode(false);
        shenzhenBuNode.setIsHasAuthority(true);
        shenzhenBuNode.setIsSelected(true);
        shenzhenBuNode.setChildList(new ArrayList<>());

        RelationTreeNode beijingBuNode = itCuNodes.get(1);
        beijingBuNode.setNodeID(8);
        beijingBuNode.setNodeName("北京交付部");
        beijingBuNode.setNodeType("CU");
        beijingBuNode.setNodePath(":1:3:");
        beijingBuNode.setIsTeamNode(false);
        beijingBuNode.setIsHasAuthority(true);
        beijingBuNode.setIsSelected(true);
        beijingBuNode.setChildList(new ArrayList<>());

        RelationTreeNode shanghaiBuNode = itCuNodes.get(2);
        shanghaiBuNode.setNodeID(9);
        shanghaiBuNode.setNodeName("上海交付部");
        shanghaiBuNode.setNodeType("CU");
        shanghaiBuNode.setNodePath(":1:3:");
        shanghaiBuNode.setIsTeamNode(false);
        shanghaiBuNode.setIsHasAuthority(true);
        shanghaiBuNode.setIsSelected(true);
        shanghaiBuNode.setChildList(new ArrayList<>());

        List<String> rst = new ArrayList<>();
        covertPostOption2Path(baseNode, rst);
        System.out.println(rst);
    }
}
