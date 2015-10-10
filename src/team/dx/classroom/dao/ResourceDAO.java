package team.dx.classroom.dao;

import java.util.List;

import team.dx.classroom.domain.Resource;

public interface ResourceDAO {

	public List<Resource> getResources(String condition, Object ... args);
	
	public void updateResource(Resource resource);
	
	public void deleteResource(String id);
	
	public void addResource(Resource resource);
}
