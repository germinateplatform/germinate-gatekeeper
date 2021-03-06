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

package jhi.gatekeeper.server.util;

import org.flywaydb.core.*;
import org.flywaydb.core.api.*;

import java.util.logging.*;

import javax.servlet.*;

import jhi.database.server.*;

public class ApplicationListener implements ServletContextListener
{
	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		PropertyReader.initialize();

		String database = PropertyReader.getProperty(PropertyReader.DATABASE_NAME);
		try
		{
			Logger.getLogger("").log(Level.INFO, "RUNNING FLYWAY on: " + database);
			Flyway flyway = new Flyway();
			flyway.setTable("schema_version");
			flyway.setValidateOnMigrate(false);
			flyway.setDataSource(Database.DatabaseType.MYSQL.getConnectionString() + DatabaseUtils.getServerString(), PropertyReader.getProperty(PropertyReader.DATABASE_USERNAME), PropertyReader.getProperty(PropertyReader.DATABASE_PASSWORD));
			flyway.setLocations("classpath:jhi.gatekeeper.server.database.migration");
			flyway.setBaselineOnMigrate(true);
			flyway.migrate();
			flyway.repair();
		}
		catch (FlywayException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
		/* Remember to stop the property and image file watcher */
		PropertyReader.stopFileWatcher();
	}
}
