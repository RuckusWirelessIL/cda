package pt.webdetails.cda.formula;

import java.util.HashMap;
import java.util.Map;

import org.pentaho.reporting.libraries.formula.DefaultFormulaContext;

import pt.webdetails.cda.CdaEngine;
import pt.webdetails.cpf.session.ISessionUtils;


public class DefaultSessionFormulaContext extends DefaultFormulaContext {

    private Map<String, ICdaParameterProvider> providers = new HashMap<String, ICdaParameterProvider>();

    public DefaultSessionFormulaContext(Map<String, ICdaParameterProvider> ps) {
        if (ps == null || ps.size() == 0) {
            ISessionUtils utils = CdaEngine.getEnvironment().getSessionUtils();
            if (utils != null) {
                this.providers.put("security:", new CdaSecurityParameterProvider(utils));
                this.providers.put("session:", new CdaSessionParameterProvider(utils));
            }
            this.providers.put("system:", new CdaSystemParameterProvider());
        } else {
            this.providers = ps;
        }
    }

    @Override
    public Object resolveReference(final Object name) {
        if (name instanceof String) {
            String paramName = ((String) name).trim();
            for (String prefix : providers.keySet()) {
                if (paramName.startsWith(prefix)) {
                    // logger.debug("Found provider for prefix: " + prefix +
                    // " Provider: " + providers.get(prefix));
                    paramName = paramName.substring(prefix.length());
                    Object value = providers.get(prefix).getParameter(paramName);
                    return value;
                }
            }
        }
        return super.resolveReference(name);
    }
}
