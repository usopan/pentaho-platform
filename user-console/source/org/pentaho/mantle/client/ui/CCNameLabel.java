package org.pentaho.mantle.client.ui;

import org.pentaho.mantle.client.admin.JsCCCUserDetails;
import org.pentaho.mantle.client.commands.LogoutCommand;

import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.NavPills;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CCNameLabel extends HorizontalPanel {
	private JsCCCUserDetails userDetails ;
	private Label ccNameLabel;
	private Label userNameLabel;
	public CCNameLabel() {
		
		userDetails = this.getDetails() ;
		if(userDetails != null) {
			ccNameLabel = new Label((userDetails.getCcname()));
			userNameLabel = new Label((userDetails.getUsername()));
		} else {
			ccNameLabel = new Label("CC Name");
			userNameLabel = new Label("User Name");
		}
		
		VerticalPanel labelPanel = new VerticalPanel();
		NavPills navPills = new NavPills();
		NavLink logoutToLsButton = new NavLink("Logout");
		navPills.add(logoutToLsButton);
//		logoutToLsButton.addStyleName("gwt-button");
		ClickHandler logoutHandler = new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				new LogoutCommand().execute();
			}
			
		};
		logoutToLsButton.addClickHandler(logoutHandler);
		labelPanel.add(userNameLabel);
		labelPanel.add(ccNameLabel);
		this.add(labelPanel);
		this.add(navPills);
		this.setSpacing(2);
		this.addStyleName("puc-horizontal-split-panel .hsplitter");
	}

	public JsCCCUserDetails getDetails() 
	{	 
		final String url = GWT.getHostPageBaseURL() + "plugin/cws/api/tenant/cccuserdetails";//$NON-NLS-1$
	    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
	    try {
	     // builder.setHeader("If-Modified-Since", "01 Jan 1970 00:00:00 GMT");
	    	builder.setCallback(new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					userDetails = JsCCCUserDetails.parseJsonString(response.getText());
					ccNameLabel.setText(userDetails.getCcname());
					userNameLabel.setText(userDetails.getUsername());
					
				}
				
				@Override
				public void onError(Request request, Throwable exception) {
					exception.printStackTrace();
				}
			});
	     Request request = builder.send();
	    } catch (RequestException e) {
	      e.printStackTrace();
	    }
	    return userDetails;
	}
}
