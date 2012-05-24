var ZEU = {}

ZEU.registerViewport = function(host, gameId) {
  this.socket = io.connect(host);
  this.socket.emit('viewport_register', {'game_id': gameId });
  this.host = host;
  this.gameId = gameId;
  this.getGameInfo();

  this.socket.on('refresh_player_list', function(data) {
    ZEU.getGameInfo();
  });

  this.socket.on('controller_action', function(data) {
  });
}

ZEU.matchPlayer = function(data, i) {
  return this.players[i] == data['username']
}

ZEU.setGameInfo = function(data) {
  this.players = data['players'];
}

ZEU.getGameInfo = function() {
  $.get("/games/" + this.gameId,
    function(data) {
      ZEU.setGameInfo(data);
    },
    "json"
  );
}
