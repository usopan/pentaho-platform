package org.pentaho.mantle.client.admin;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.json.client.JSONObject;

public class JsCCCUserDetails extends JavaScriptObject {
	protected JsCCCUserDetails(){
	}
	public final String getJSONString() {
	    return new JSONObject(this).toString();
	  }
	 public final native void setUsername(String username)  /*-{ this.username = username; }-*/; //
	 public final native void setCcname(String ccname)  /*-{ this.ccname = ccname; }-*/; //
	 public final native String getUsername() /*-{ return this.username; }-*/;
	 public final native String getCcname() /*-{ return this.ccname; }-*/;
	 
	  public static final JsCCCUserDetails parseJsonString(final String jsonString) {
	    return (JsCCCUserDetails) parseConfig(JsonUtils.escapeJsonForEval(jsonString));
	  }
	 
	  private static final  JavaScriptObject parseConfig(String json)
	  {
		  return JsonUtils.safeEval(json);
	  }
}
