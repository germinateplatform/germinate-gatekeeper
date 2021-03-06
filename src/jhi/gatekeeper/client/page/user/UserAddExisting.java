/*
 * Copyright 2017 Information and Computational Sciences,
 * The James Hutton Institute.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jhi.gatekeeper.client.page.user;

import com.google.gwt.core.client.*;
import com.google.gwt.event.shared.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;

import jhi.gatekeeper.client.util.event.*;
import jhi.gatekeeper.client.widget.select.*;
import jhi.gatekeeper.shared.bean.*;

/**
 * @author Sebastian Raubach
 */
public class UserAddExisting extends Composite
{
	private static UserAddViewUiBinder ourUiBinder = GWT.create(UserAddViewUiBinder.class);
	@UiField
	DatabaseSystemListBox databaseName;
	@UiField
	UserTypeListBox accessLevel;
	private HandlerRegistration handlerRegistration;
	private User user;

	public UserAddExisting()
	{
		initWidget(ourUiBinder.createAndBindUi(this));

		this.setVisible(false);

		handlerRegistration = GatekeeperEventBus.BUS.addHandler(UserSelectionEvent.TYPE, event -> setUser(event.getUser()));
	}

	private void setUser(User user)
	{
		/* This view is visible after user selection */
		this.setVisible(true);

		this.user = user;

		if (user != null)
		{
			databaseName.update();
		}
	}

	public DatabasePermission getPermission()
	{
		return new DatabasePermission(user, databaseName.getValue(), accessLevel.getValue());
	}

	@Override
	protected void onUnload()
	{
		if (handlerRegistration != null)
			handlerRegistration.removeHandler();

		super.onUnload();
	}

	interface UserAddViewUiBinder extends UiBinder<FlowPanel, UserAddExisting>
	{
	}
}