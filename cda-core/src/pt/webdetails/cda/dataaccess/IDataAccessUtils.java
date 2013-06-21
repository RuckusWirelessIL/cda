/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/. */

package pt.webdetails.cda.dataaccess;

import org.pentaho.reporting.engine.classic.core.ReportEnvironmentDataRow;
import org.pentaho.reporting.engine.classic.core.modules.misc.datafactory.sql.ConnectionProvider;
import org.pentaho.reporting.engine.classic.extensions.datasources.kettle.KettleTransformationProducer;
import org.pentaho.reporting.engine.classic.extensions.datasources.mondrian.AbstractNamedMDXDataFactory;
import org.pentaho.reporting.engine.classic.extensions.datasources.mondrian.DataSourceProvider;
import org.pentaho.reporting.engine.classic.extensions.datasources.pmd.PmdDataFactory;
import org.pentaho.reporting.libraries.base.config.Configuration;

import pt.webdetails.cda.connections.kettle.TransFromFileConnectionInfo;
import pt.webdetails.cda.connections.mondrian.MondrianConnection;
import pt.webdetails.cda.connections.mondrian.MondrianJndiConnectionInfo;
import pt.webdetails.cda.connections.sql.SqlJndiConnectionInfo;

/**
 * @deprecated IDataAccessUtils: who needs architecture when you've got magic beans!
 * <br>* roles, catalogs, and formula contexts sold seperately
 */
public interface IDataAccessUtils {

  //in MdxDataAccess.getDataFactory
  public void setMdxDataFactoryBaseConnectionProperties(MondrianConnection connection, AbstractNamedMDXDataFactory mdxDataFactory);

  //in MqlDataAccess.getDataFactory
  public void setConnectionProvider(PmdDataFactory returnDataFactory);

  //in PREDataAccess.performRawQuery
  public ReportEnvironmentDataRow createEnvironmentDataRow(Configuration configuration);

  //in connections.kettle.TransFromFileConnection.createTransformationProducer
  public KettleTransformationProducer createKettleTransformationProducer(TransFromFileConnectionInfo connectionInfo, String query);

  //in sql.JndiConnection.getInitializedConnectionProvider
  public ConnectionProvider getJndiConnectionProvider(SqlJndiConnectionInfo connectionInfo);

  //in mondrian.JndiConnection.getInitializedConnectionProvider
  public DataSourceProvider getMondrianJndiDatasourceProvider(MondrianJndiConnectionInfo connectionInfo);

}
