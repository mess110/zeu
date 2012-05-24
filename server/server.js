var app = require('express').createServer()
  , io = require('socket.io').listen(app)
  , helpers = require('./helpers');

var viewports = {};
var controllers = {};
var games = {};
var gameId = 0;

app.get('/info', function (req, res) {
  res.contentType('json');
  status = {
    "ok": true,
    "version": "0.1.0",
    "viewports": countHash(viewports),
    "controllers": countHash(controllers),
    "games": games
  };
  res.send(status);
});

app.post('/new_game', function (req, res) {
  gameId += 1;
  games[gameId] = {
    'name': 'game1',
    'type': 'pong',
    'id': gameId,
    'players': [],
    'viewports': {}
  };
  res.contentType('json');
  res.send(games[gameId]);
});

io.sockets.on('connection', function (socket) {
  socket.on('disconnect', function (data) {
    dc(viewports, socket);
    dc(controllers, socket);
  });

  socket.on('controller_action', function (data) {
    // if the game doesn't exist, dc the socket
    if (!controllers.hasOwnProperty(socket.id)) {
      socket.disconnect();
      return;
    }

    players = games[data['game_id']]['players'];
    if (players.indexOf(data['username']) == -1) {
      players.push(data['username']);
    }

    // send it to all the viewports
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
      socket.disconnect();
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
      socket.disconnect();
      return;
    }

    controllers[socket.id] = socket;
  });
});

app.listen(5000);
