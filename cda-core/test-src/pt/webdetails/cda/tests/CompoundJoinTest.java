package pt.webdetails.cda.tests;

import java.io.ByteArrayOutputStream;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import org.junit.BeforeClass;
import pt.webdetails.cda.CdaEngine;
import pt.webdetails.cda.CoreBeanFactory;
import pt.webdetails.cda.DefaultCdaEnvironment;
import pt.webdetails.cda.InitializationException;
import pt.webdetails.cda.exporter.JsonExporter;
import pt.webdetails.cda.query.QueryOptions;
import pt.webdetails.cda.settings.CdaSettings;
import pt.webdetails.cda.settings.SettingsManager;

/**
 * Created by IntelliJ IDEA.
 * User: pedro
 * Date: Feb 15, 2010
 * Time: 7:53:13 PM
 */
// TODO: we need a base class this kind of tests
public class CompoundJoinTest
{

  @BeforeClass
  public static void init() throws InitializationException {
      CoreBeanFactory cbf = new CoreBeanFactory("cda.spring.xml");
      DefaultCdaEnvironment env = new DefaultCdaEnvironment(cbf);
      CdaEngine.init(env);
  }

  @Test
  public void testCompoundQuery() throws Exception
  {

      final SettingsManager settingsManager = SettingsManager.getInstance();

      URL file = this.getClass().getResource("sample-join.cda");
      Assert.assertNotNull(file);

      final CdaSettings cdaSettings = settingsManager.parseSettingsFile(file.getPath());

      QueryOptions queryOptions = new QueryOptions();
      queryOptions.setDataAccessId("3");
      queryOptions.setOutputType("json");

      ByteArrayOutputStream out = new ByteArrayOutputStream();
      CdaEngine.getInstance().doQuery(out, cdaSettings, queryOptions);
      final String result = out.toString(JsonExporter.getEncoding());

      // TODO: check result with a proper comparator
      // json formatting and decimal places are good sources of trouble otherwise
      //final String expected = 
      //    "{\"queryInfo\":"
      //    + "{\"totalRows\":\"12\"},"
      //    + "\"resultset\":[[2003,\"Cancelled\",75132.15999999999,225396.48000000007,150264.32000000008],"
      //        + "[2003,\"Resolved\",28550.59,85651.76999999999,57101.17999999999],"
      //        + "[2003,\"Shipped\",3573701.2500000014,1.0721103750000002E7,7147402.5000000006],"
      //        + "[2004,\"Cancelled\",187195.13000000003,561585.3900000001,374390.26000000007],"
      //        + "[2004,\"On Hold\",26260.210000000003,78780.62999999999,52520.419999999987],"
      //        + "[2004,\"Resolved\",24078.610000000004,72235.82999999999,48157.219999999986],"
      //        + "[2004,\"Shipped\",4750205.889999998,1.4250617669999992E7,9500411.779999994],"
      //        + "[2005,\"Disputed\",72212.86,216638.58,144425.72],"
      //        + "[2005,\"In Process\",144729.96000000002,434189.87999999995,289459.91999999993],"
      //        + "[2005,\"On Hold\",152718.97999999995,458156.94,305437.96000000005],"
      //        + "[2005,\"Resolved\",98089.08000000002,294267.24,196178.15999999998],"
      //        + "[2005,\"Shipped\",1513074.4600000002,4539223.38,3026148.9199999998]],"
      //    + "\"metadata\":["
      //        + "{\"colIndex\":0,\"colType\":\"Numeric\",\"colName\":\"YEAR_ID\"},"
      //        + "{\"colIndex\":1,\"colType\":\"String\",\"colName\":\"STATUS\"},"
      //        + "{\"colIndex\":2,\"colType\":\"Numeric\",\"colName\":\"TOTALPRICE\"},"
      //        + "{\"colIndex\":3,\"colType\":\"Numeric\",\"colName\":\"TRIPLEPRICE\"},"
      //        + "{\"colIndex\":4,\"colType\":\"Numeric\",\"colName\":\"PriceDiff\"}]}";
      //Assert.assertEquals("Query result mismatch", expected, result);

      // let's just settle for quantity for now
      JSONObject json = new JSONObject(result);
      Assert.assertEquals("Column count", ((JSONArray) json.get("metadata")).length(), 5);
      Assert.assertEquals("Row count", ((JSONArray) json.get("resultset")).length(), 12);

  }

}