var app = require('express').createServer()
  , io = require('socket.io').listen(app);

var viewports = {}

app.listen(5000);

app.get('/', function (req, res) {
  res.contentType('json');
  status = {
    "name": "zeu"
  };
  res.send(status);
});

io.sockets.on('connection', function (socket) {
  console.log('connncetd');
  socket.on('controller_action', function (data) {
    console.log(data);
    for (var id in viewports) {
      viewports[id].emit('controller_action', data);
    }
  });

  socket.on('viewport_register', function() {
    viewports[socket.id] = socket;
  });
});