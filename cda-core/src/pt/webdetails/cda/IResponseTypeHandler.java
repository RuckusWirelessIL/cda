package pt.webdetails.cda;

import java.util.Locale;

// TODO: ?
public interface IResponseTypeHandler {

    public void setResponseHeaders(final String mimeType, final int cacheDuration, final String attachmentName);

    public boolean hasResponse();

    public Locale getLocale();
}
