var app = require('express').createServer()
  , io = require('socket.io').listen(app);

var viewports = {}
var controllers = {}

// TODO find a better way
// viewports.length doesn't work
countHash = function(hash) {
  count = 0;
  for (var id in hash) {
    count++;
  }
  return count;
}

app.listen(5000);

app.get('/', function (req, res) {
  res.contentType('json');
  status = {
    "name": "zeu",
    "viewports": countHash(viewports),
    "controllers": countHash(controllers)
  };
  res.send(status);
});

io.sockets.on('connection', function (socket) {
  socket.on('disconnect', function (data) {
    if (viewports.hasOwnProperty(socket.id)) {
      delete viewports[socket.id];
    }
    if (controllers.hasOwnProperty(socket.id)) {
      delete controllers[socket.id];
    }
  });

  socket.on('controller_action', function (data) {
    for (var id in viewports) {
      viewports[id].emit('controller_action', data);
    }
  });

  socket.on('viewport_register', function() {
    viewports[socket.id] = socket;
  });

  socket.on('controller_register', function() {
    controllers[socket.id] = socket;
  });
});
