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

package jhi.gatekeeper.client.cell;

import com.google.gwt.cell.client.*;
import com.google.gwt.core.client.*;
import com.google.gwt.safehtml.client.*;
import com.google.gwt.safehtml.shared.*;

import jhi.gatekeeper.client.util.*;

/**
 * @author Sebastian Raubach
 */
public abstract class AbstractListCell<T> extends AbstractCell<T>
{
	public static final UserTemplates USER_TEMPLATES = GWT.create(UserTemplates.class);

	public interface UserTemplates extends SafeHtmlTemplates
	{
		@Template("<i class=\"" + Classes.FA + " " + Classes.FA_FW + " " + Classes.FA_USER_SECRET + "\"></i><span>&nbsp;{0}</span></div>")
		SafeHtml admin(SafeHtml value);

		@Template("<i class=\"" + Classes.FA + " " + Classes.FA_FW + " " + Classes.FA_USER + "\"></i><span>&nbsp;{0}</span></div>")
		SafeHtml user(SafeHtml value);

		@Template("<i class=\"" + Classes.FA + " " + Classes.FA_FW + " " + Classes.FA_USER + "\"></i><span>&nbsp;{0}</span><span class='pull-right'>{1}</span></div>")
		SafeHtml userDate(SafeHtml value, SafeHtml date);

		@Template("<a href='{0}'>{1}</a>")
		SafeHtml anchor(SafeUri url, String text);
	}
}
