/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/. */
package pt.webdetails.cda;

import org.pentaho.platform.engine.core.system.PentahoSessionHolder;
import org.pentaho.reporting.libraries.formula.FormulaContext;

import pt.webdetails.cda.cache.IQueryCache;
import pt.webdetails.cda.utils.framework.PluginUtils;


public class PentahoCdaEnvironment extends DefaultCdaEnvironment {

  public PentahoCdaEnvironment() throws InitializationException {
    super();
  }

  private IQueryCache cacheImpl;

  //This is kept here for legacy reasons. CDC is writing over plugin.xml to 
  //switch cache types. It should be changed to change the cda.spring.xml.
  //While we don't, we just keep the old method for getting the cache
  // ^ we won't do this until the change brings improvements
  @Override  
  public IQueryCache getQueryCache() {
    try {
      if (cacheImpl == null) {
        cacheImpl = PluginUtils.getPluginBean("cda.", IQueryCache.class);
      }
      return cacheImpl;
    } catch (Exception e) {
      logger.error(e);
    }

    return super.getQueryCache();
  }

  @Override
  public FormulaContext getFormulaContext() {
    return new CdaSessionFormulaContext(PentahoSessionHolder.getSession());
  }
}
