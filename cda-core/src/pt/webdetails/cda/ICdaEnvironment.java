package pt.webdetails.cda;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.pentaho.reporting.libraries.formula.FormulaContext;

import pt.webdetails.cda.cache.ICacheScheduleManager;
import pt.webdetails.cda.cache.IQueryCache;
import pt.webdetails.cda.connections.mondrian.IMondrianRoleMapper;
import pt.webdetails.cda.dataaccess.ICubeFileProviderSetter;
import pt.webdetails.cda.dataaccess.IDataAccessUtils;
import pt.webdetails.cda.settings.IResourceKeyGetter;
import pt.webdetails.cpf.IPluginCall;
import pt.webdetails.cpf.messaging.IEventPublisher;
import pt.webdetails.cpf.repository.IRepositoryAccess;
import pt.webdetails.cpf.repository.IRepositoryFile;
import pt.webdetails.cpf.session.ISessionUtils;

/**
 * the all-purpose factory
 */
public interface ICdaEnvironment {

  public void init() throws InitializationException;

  /**
   * @deprecated just look at it
   */
  public ICubeFileProviderSetter getCubeFileProviderSetter();

  public IQueryCache getQueryCache();

  public IMondrianRoleMapper getMondrianRoleMapper();

  public String getCdaConfigFile(String fileName);

  public FormulaContext getFormulaContext();

  public Properties getCdaComponents();

  public List<IRepositoryFile> getComponentsFiles();

  public IEventPublisher getEventPublisher();

  public ISessionUtils getSessionUtils();

  public IRepositoryAccess getRepositoryAccess();

  public IDataAccessUtils getDataAccessUtils();

  public IResourceKeyGetter getResourceKeyGetter();

  public IPluginCall createPluginCall(String plugin, String method, Map<String, Object> params);

  public boolean supportsCacheScheduler();

  public ICacheScheduleManager getCacheScheduler();

}