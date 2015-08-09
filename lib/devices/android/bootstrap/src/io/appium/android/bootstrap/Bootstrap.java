package io.appium.android.bootstrap;

import java.io.File;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import io.appium.android.bootstrap.exceptions.SocketServerException;
import io.appium.android.bootstrap.handler.Find;

/**
 * The Bootstrap class runs the socket server.
 *
 */
public class Bootstrap extends UiAutomatorTestCase {

  public void testRunServer() {
    //
    com.tns.Platform.init2(new File("/data/local/tmp/nsapp"), false);
    String src =
    "(function() {\n" + 
    " var t = 'appium';\n" +
    " var m = 'Hello from NativeScript!';\n" +
    " android.util.Log.d(t, m);\n" +
    "})()";
    com.tns.Platform.runScript(src);
    //
    Find.params = getParams();
    boolean disableAndroidWatchers = Boolean.parseBoolean(getParams().getString("disableAndroidWatchers"));

    SocketServer server;
    try {
      server = new SocketServer(4724);
      server.listenForever(disableAndroidWatchers);
    } catch (final SocketServerException e) {
      Logger.error(e.getError());
      System.exit(1);
    }

  }
}
