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

package jhi.gatekeeper.client.page.account;

import com.google.gwt.core.client.*;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.rpc.*;
import com.google.gwt.user.client.ui.*;

import jhi.gatekeeper.client.i18n.*;
import jhi.gatekeeper.client.service.*;
import jhi.gatekeeper.client.util.*;
import jhi.gatekeeper.shared.*;
import jhi.gatekeeper.shared.bean.*;

/**
 * @author Sebastian Raubach
 */
public class ActivateUserView extends Composite
{
	private static ActivateUserViewUiBinder ourUiBinder = GWT.create(ActivateUserViewUiBinder.class);
	@UiField
	Label label;

	public ActivateUserView()
	{
		initWidget(ourUiBinder.createAndBindUi(this));

		String key = Window.Location.getParameter("key");

		if (!StringUtils.isEmpty(key))
		{
			UserService.Instance.getInstance().activateUser(Cookie.getRequestProperties(), key, new AsyncCallback<ActivationDecision>()
			{
				@Override
				public void onFailure(Throwable caught)
				{
					label.setText(I18n.LANG.activateUserActivationFailed(caught.getLocalizedMessage()));
				}

				@Override
				public void onSuccess(ActivationDecision result)
				{
					switch (result)
					{
						case GRANTED:
							label.setText(I18n.LANG.activateUserActivationSuccessful());
							break;
						case AWAITS_APPROVAL:
							label.setText(I18n.LANG.activateUserActivationAwaitingApproval());
							break;
						case ERROR:
							label.setText(I18n.LANG.activateUserActivationNotPossible());
							break;
					}
				}
			});
		}
		else
		{
			label.setText(I18n.LANG.activateUserInvalidRequest());
		}
	}

	interface ActivateUserViewUiBinder extends UiBinder<Panel, ActivateUserView>
	{
	}
}