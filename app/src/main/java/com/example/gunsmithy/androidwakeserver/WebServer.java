package com.example.gunsmithy.androidwakeserver;

import android.content.Context;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Dylan Kauling on 2016-08-01.
 */
public class WebServer extends NanoHTTPD {

    private static final Logger LOG = Logger.getLogger(WebServer.class.getName());

    public static void main(String[] args) {
        ServerRunner.run(WebServer.class);
    }

    WakeLockClass wakeLockClassInstance;

    public WebServer(Context appContext) {
        super(8080);
        wakeLockClassInstance = new WakeLockClass(appContext);
    }

    @Override
    public Response serve(IHTTPSession session) {
        Method method = session.getMethod();
        String uri = session.getUri();
        WebServer.LOG.info(method + " '" + uri + "' ");

        String msg = "<html><body><h1>Hello server</h1>\n";
        Map<String, String> parms = session.getParms();
        if (parms.get("COMMAND") == null)
        {
            msg += "Missing COMMAND param!</p>";
        }
        else if (parms.get("COMMAND").equals("WAKE"))
        {
            wakeLockClassInstance.wakeDevice();
            msg += "<p>WAKED!</p>";
        }
        else
        {
            msg += "Not WAKE in COMMAND param!</p>";
        }

        msg += "</body></html>\n";

        return newFixedLengthResponse(msg);
    }
}
