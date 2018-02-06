
    // Enable pusher logging - don't include this in production
    // Pusher.logToConsole = true;

    // Open a connection to Pusher
    var pusher = new Pusher('d756bf87f8141ec89336', {
      cluster: 'eu',
      encrypted: true
    });

    // Subscribe to a channel
    var channel = pusher.subscribe('my-channel');
    // Listen for events on your channel
    channel.bind('my-event', function(data) {
      alert(data.message);
    });
    channel.bind('ghost_msg', function(data) {
      document.getElementById("textelement").innerHTML = data.message;
    });
    channel.bind('ghost_pic', function(data) {
      document.getElementById("pic").src = data.message;
    });

    // Utility event that fires for all state changes
    pusher.connection.bind('state_change', function(states) {
    // states = {previous: 'oldState', current: 'newState'}
    $('div#status').text("Pusher's current state is " + states.current);

	// Color codes for connection status
    if (states.current == "connected") {
        document.getElementById("status_bg").className = "p-3 mb-2 bg-success text-white";
    } else {
        document.getElementById("status_bg").className = "p-3 mb-2 bg-warning text-dark";
    }

    });

    var notificationsChannel = pusher.subscribe('notifications');

    notificationsChannel.bind('new_notification', function(notification){
      var message = notification.message;
      $('p.notification').text(message);

      if (message.includes("warning")) {
          document.getElementById("notification_bg").className = "p-3 mb-2 bg-danger text-white";
      }
    });
    
    notificationsChannel.bind('new_broadcast', function(notification){
      var message = notification.message;
      $('p#br_msg').text(message);
      $('p#br_default').text("");
      
      if (message == null) {
          document.getElementById('br_default').innerHTML = "No PSA has been issued by the control room.";
          document.getElementById('br_msg').innerHTML = " ";
      }
    });