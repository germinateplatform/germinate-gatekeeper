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

package jhi.gatekeeper.server.service;

import java.util.*;

import javax.servlet.annotation.*;

import jhi.database.shared.exception.*;
import jhi.database.shared.util.*;
import jhi.gatekeeper.client.service.*;
import jhi.gatekeeper.server.manager.*;
import jhi.gatekeeper.shared.*;
import jhi.gatekeeper.shared.bean.*;
import jhi.gatekeeper.shared.exception.*;

/**
 * @author Sebastian Raubach
 */
@WebServlet(urlPatterns = {"/gatekeeper/institution"})
public class InstitutionServiceImpl extends AbstractServletImpl implements InstitutionService
{
	@Override
	public PaginatedResult<List<Institution>> getInstitutionList(RequestProperties properties, Pagination pagination) throws InvalidSessionException, InsufficientPermissionsException, DatabaseException
	{
		checkSessionAndPermissions(properties);

		if (pagination == null)
			pagination = Pagination.DEFAULT;

		return InstitutionManager.getList(pagination);
	}
}
