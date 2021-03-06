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
import jhi.gatekeeper.client.widget.pagination.*;
import jhi.gatekeeper.shared.bean.*;

/**
 * @author Sebastian Raubach
 */
public class UserPermissionsView extends Composite
{
	private static UserPermissionsViewUiBinder ourUiBinder = GWT.create(UserPermissionsViewUiBinder.class);
	private final HandlerRegistration handlerRegistrationUser;
	private final HandlerRegistration handlerRegistrationPermission;
	@UiField
	FlowPanel table;
	private User user;
	public UserPermissionsView()
	{
		initWidget(ourUiBinder.createAndBindUi(this));

		this.setVisible(false);

		handlerRegistrationUser = GatekeeperEventBus.BUS.addHandler(UserSelectionEvent.TYPE, event ->
		{
			user = event.getUser();
			setUser();
		});
		handlerRegistrationPermission = GatekeeperEventBus.BUS.addHandler(DatabasePermissionListChangeEvent.TYPE, event -> setUser());
	}

	private void setUser()
	{
		this.setVisible(user != null);

		table.clear();

		if (user != null)
		{
			table.add(new DatabasePermissionsTable(user.getId(), DatabasePermissionsTable.Type.USER));
		}
	}

	@Override
	protected void onUnload()
	{
		if (handlerRegistrationUser != null)
			handlerRegistrationUser.removeHandler();

		if (handlerRegistrationPermission != null)
			handlerRegistrationPermission.removeHandler();

		super.onUnload();
	}

	interface UserPermissionsViewUiBinder extends UiBinder<HTMLPanel, UserPermissionsView>
	{

	}
}