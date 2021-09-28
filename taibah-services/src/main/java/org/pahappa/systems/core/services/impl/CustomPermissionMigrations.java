package org.pahappa.systems.core.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.pahappa.systems.models.security.TBPermissionInterpreter;
import org.sers.webutils.model.RecordStatus;
import org.sers.webutils.model.migrations.Migration;
import org.sers.webutils.model.security.Permission;
import org.sers.webutils.model.security.Role;
import org.sers.webutils.server.core.dao.PermissionDao;
import org.sers.webutils.server.core.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomPermissionMigrations {

	@Autowired
	PermissionDao permissionDao;

	@Autowired
	RoleDao roleDao;

	@Migration(orderNumber = 1)
	public void savePermissions() {

            for (Permission permission : TBPermissionInterpreter.reflectivelyGetPermissions()) {
                if (permissionDao.searchUniqueByPropertyEqual("name", permission.getName()) == null) {
                    
                    try {
                        permission.setRecordStatus(RecordStatus.ACTIVE);
                        permission.setDateCreated(new Date());
                        permission.setDateChanged(new Date());
                        Permission saved = permissionDao.mergeBG(permission);
                        
                        Role role = new Role();
                        role.setRecordStatus(RecordStatus.ACTIVE);
                        role.setDescription(permission.getDescription());
                        role.setName(permission.getName());
                        role.setPermissions(new HashSet<>(Arrays.asList(new Permission[] { saved })));
                        roleDao.mergeBG(role);
                    } catch (Exception exe) {
                        System.out.println("Permission already exists");
                    }
                }
            }
	}
}
