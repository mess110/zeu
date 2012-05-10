var app = require('express').createServer()
  , io = require('socket.io').listen(app)
  , helpers = require('./helpers');

var viewports = {}
var controllers = {}

app.get('/', function (req, res) {
  res.contentType('json');
  status = {
    "name": "zeu",
    "version": "0.1.0",
    "viewports": countHash(viewports),
    "controllers": countHash(controllers)
  };
  res.send(status);
});

io.sockets.on('connection', function (socket) {
  socket.on('disconnect', function (data) {
    dc(viewports, socket);
    dc(controllers, socket);
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

app.listen(5000);
