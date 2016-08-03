# AndroidWakeServer
A background Android service to wake/unlock the device on HTTP request

Runs HTTP server as on ongoing service in the background with a notification to indicate its operation.  
Starts with manual button in the app or automatically on subsequent reboots.  
Waits for a GET request to the device on port 8080 with the parameter COMMAND containing WAKE.  
This command wakes/unlocks the device and opens [The Sensorian website](http://sensorian.io/) in the browser.  
May sometimes have issues where the screen goes black and becomes unresponsive, requiring a reboot.  
