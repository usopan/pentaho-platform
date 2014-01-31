package org.pentaho.mantle.client.objects;

public class CCCPermissions {
	private static native String getUsername()
	  /*-{  
	    return window.top.SESSION_NAME;
	  }-*/;
	  
	  public static boolean isCCCLogicAdmin() {
		  if(getUsername().startsWith("cc_")) {
			  return false;
		  } else 
			  return true;
	  }
}
