var app = require('express').createServer()
  , io = require('socket.io').listen(app)
  , helpers = require('./helpers');

var viewports = {}
var controllers = {}
var games = {}

app.get('/info', function (req, res) {
  res.contentType('json');
  status = {
    "name": "zeu",
    "version": "0.1.0",
    "viewports": countHash(viewports),
    "controllers": countHash(controllers),
    "games": games
  };
  res.send(status);
});

app.get('/game/:id', function (req, res) {
  games[req.params.id] = {};
  res.contentType('json');
  res.send(req.params.id);
});

io.sockets.on('connection', function (socket) {
  socket.on('disconnect', function (data) {
    dc(viewports, socket);
    dc(controllers, socket);
  });

  socket.on('controller_action', function (data) {
    if (!controllers.hasOwnProperty(socket.id)) {
      return;
    }

    for (var id in viewports) {
      viewportGameId = viewports[id]['game_id'];
      if (viewportGameId == data['game_id']) {
        viewportSocket = viewports[id]['socket'];
        viewportSocket.emit('controller_action', data);
      }
    }
  });

  socket.on('viewport_register', function (data) {
    game_id = data['game_id'];
    if (!games.hasOwnProperty(game_id)) {
      return;
    }

    viewports[socket.id] = {
      'socket': socket,
      'game_id': game_id
    };
  });

  socket.on('controller_register', function (data) {
    game_id = data['game_id'];
    if (!games.hasOwnProperty(game_id)) {
      return;
    }

    controllers[socket.id] = socket;
  });
});

app.listen(5000);
