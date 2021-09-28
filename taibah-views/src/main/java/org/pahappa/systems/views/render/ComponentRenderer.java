package org.pahappa.systems.views.render;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.pahappa.systems.core.services.SystemSettingService;
import org.pahappa.systems.models.SystemSetting;
import org.pahappa.systems.models.security.PermissionConstants;
import org.sers.webutils.model.security.User;
import org.sers.webutils.server.core.utils.ApplicationContextProvider;
import org.sers.webutils.server.shared.SharedAppData;

@ManagedBean(name = "componentRenderer")
@SessionScoped
public class ComponentRenderer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean administrator = false;
	private boolean businessAdmin = false;
	private User loggedInUser;
	private SystemSetting systemSetting = new SystemSetting();
	private SystemSettingService systemSettingService;


	@PostConstruct
	public void init() {
		this.loggedInUser = SharedAppData.getLoggedInUser();
		this.administrator = loggedInUser.hasAdministrativePrivileges();
		this.businessAdmin = loggedInUser.hasRole(PermissionConstants.PERM_BUSINESS_ADMIN);
		
		
		this.systemSettingService = ApplicationContextProvider.getBean(SystemSettingService.class);
		
		
		
		if(this.systemSettingService.getSystemSettings() != null) {
			this.systemSetting = this.systemSettingService.getSystemSettings();
		}
		
	}

	/**
	 * @return the loggedInUser
	 */
	public User getLoggedInUser() {
		return loggedInUser;
	}

	/**
	 * @param loggedInUser the loggedInUser to set
	 */
	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	/**
	 * @return the administrator
	 */
	public boolean isAdministrator() {
		return administrator;
	}

	/**
	 * @param administrator the administrator to set
	 */
	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}
	
	/**
	 * @return the businessAdmin
	 */
	public boolean isBusinessAdmin() {
		return businessAdmin;
	}

	/**
	 * @param businessAdmin the businessAdmin to set
	 */
	public void setBusinessAdmin(boolean businessAdmin) {
		this.businessAdmin = businessAdmin;
	}

	

	/**
	 * @return the systemSetting
	 */
	public SystemSetting getSystemSetting() {
		return systemSetting;
	}

	/**
	 * @param systemSetting the systemSetting to set
	 */
	public void setSystemSetting(SystemSetting systemSetting) {
		this.systemSetting = systemSetting;
	}
	
}