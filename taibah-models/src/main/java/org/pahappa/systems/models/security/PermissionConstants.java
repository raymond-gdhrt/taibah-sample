package org.pahappa.systems.models.security;

public final class PermissionConstants {
	private PermissionConstants() {

	}
	
	@TBPermission(name = "Api user", description = "Has role for api users")
	public static final String PERM_API_USER = "Api User";

	@TBPermission(name = "Business Administrator", description = "Has role for business administrator")
	public static final String PERM_BUSINESS_ADMIN = "Business Administrator";
	
}
