package team.dx.classroom.dao.impl;

import java.util.List;

import team.dx.classroom.dao.BasicDAO;
import team.dx.classroom.dao.ResourceDAO;
import team.dx.classroom.domain.Resource;

public class ResourceDAOImpl extends BasicDAO<Resource> implements ResourceDAO {

	@Override
	public List<Resource> getResources(String condition, Object... args) {

		return getForList(condition, args);
	}

	@Override
	public void updateResource(Resource resource) {

		String sql = "UPDATE resource SET name = ?, uri = ?, uploadtime = ?, description = ?, uploader_id = ? WHERE id = ?";

		update(sql, resource.getName(), resource.getUri(), resource.getUploadtime(), resource.getDescription(),
				resource.getUploader().getId(), resource.getId());
	}

	@Override
	public void deleteResource(String id) {

		String sql = "DELETE FROM resource WHERE id = ?";

		update(sql, id);
	}

	@Override
	public void addResource(Resource resource) {

		String sql = "INSERT INTO resource (id, name, uri, uploadtime, description, uploader_id) VALUES (?, ?, ?, ?, ?, ?)";

		update(sql, resource.getId(), resource.getName(), resource.getUri(), resource.getUploadtime(), resource.getDescription(),
				resource.getUploader().getId());
	}

}
