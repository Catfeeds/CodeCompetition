package com.isoftstone.pmit.project.hrbp.service;

import java.util.List;
import java.util.Map;

public interface ILevelRlaBusService {
    public List<String> queryLevel(Map<String, Object> params);

    public List<String> queryMainstayLevel(Map<String, Object> params);
}
