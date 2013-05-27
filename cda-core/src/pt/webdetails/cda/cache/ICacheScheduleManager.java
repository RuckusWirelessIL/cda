/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/. */

package pt.webdetails.cda.cache;

import java.io.OutputStream;

public interface ICacheScheduleManager {
    public void handleCall(String method,String obj, OutputStream out);
}
