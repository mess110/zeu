var app = require('express').createServer()
  , io = require('socket.io').listen(app);

var viewports = {}

// TODO find a better way
// viewports.length doesn't work
calculateViewports = function() {
  count = 0;
  for (var id in viewports) {
    count++;
  }
  return count;
}

app.listen(5000);

app.get('/', function (req, res) {
  res.contentType('json');
  status = {
    "name": "zeu",
    "viewports": calculateViewports()
  };
  res.send(status);
});

io.sockets.on('connection', function (socket) {
  socket.on('disconnect', function (data) {
    if (viewports.hasOwnProperty(socket.id)) {
      delete viewports[socket.id];
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
});
