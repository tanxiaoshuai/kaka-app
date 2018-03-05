package com.sobey.service;
import java.util.Map;

public interface ISiteService {

    public Map<String, Object> findByIdList(String userid) throws Exception;

    public Map<String , Object> findByList() throws Exception;
}
