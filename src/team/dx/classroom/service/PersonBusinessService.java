package team.dx.classroom.service;

import team.dx.classroom.domain.User;

public interface PersonBusinessService {
	User findUserIsExist(String nick);
}
