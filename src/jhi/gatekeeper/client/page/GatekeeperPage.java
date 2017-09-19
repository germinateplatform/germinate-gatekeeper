/**
 *  Copyright 2017 Sebastian Raubach, Toby Philp and Paul Shaw from the
 *  Information and Computational Sciences Group at The James Hutton Institute, Dundee
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package jhi.gatekeeper.client.page;

import com.google.gwt.user.client.ui.*;

/**
 * @author Sebastian Raubach
 */
public abstract class GatekeeperPage extends Composite
{
	protected final FlowPanel panel = new FlowPanel();

	public GatekeeperPage()
	{
		initWidget(panel);
	}

	@Override
	protected void onLoad()
	{
		super.onLoad();

		setUpContent();
	}

	/**
	 * We don't allow child classes to override this method.
	 */
	@Override
	protected final void initWidget(Widget widget)
	{
		if (!panel.equals(widget))
			throw new IllegalStateException("You aren't supposed to call initWidget(Widget)...");
		else
			super.initWidget(widget);
	}

	protected abstract void setUpContent();
}
