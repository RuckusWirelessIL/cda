package pt.webdetails.cda.tests;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;
import junit.framework.TestCase;
import pt.webdetails.cda.CdaBoot;
import pt.webdetails.cda.CdaQueryComponent;
/**
 * Created by IntelliJ IDEA.
 * User: pedro
 * Date: Feb 15, 2010
 * Time: 7:53:13 PM
 */
public class CdaQueryComponentTest extends TestCase
{

  public CdaQueryComponentTest()
  {
    super();
  }

  public CdaQueryComponentTest(final String name)
  {
    super(name);
  }


  protected void setUp() throws Exception
  {

    CdaBoot.getInstance().start();

    super.setUp();
  }

  
  public void testCdaQueryComponent() throws Exception {
    CdaQueryComponent component = new CdaQueryComponent();
    URL file = this.getClass().getResource("sample-sql.cda");
    File f = new File(file.toURI());
    component.setFile(f.getAbsolutePath());
    Map<String, Object> inputs = new HashMap<String, Object>();
    inputs.put("dataAccessId", "1");
    inputs.put("paramorderDate", "2003-04-01");
    component.setInputs(inputs);

    component.validate();
    component.execute();
    Assert.assertNotNull(component.getResultSet());

    Assert.assertEquals(4, component.getResultSet().getColumnCount());
    Assert.assertEquals(3, component.getResultSet().getRowCount());
    
  }
}
