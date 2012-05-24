ZEU.socket.on('controller_action', function (data) {
  switch(data['input']) {
    case 0:
      if (ZEU.matchPlayer(data, 0)) {
        movePaddle(data, game.leftPaddle, game);
      }
      if (ZEU.isIddle(data, 0)) {
        stopPaddle(game.leftPaddle);
      }
      break;
    case 1:
      if (ZEU.matchPlayer(data, 1)) {
        movePaddle(data, game.rightPaddle, game);
      }
      if (ZEU.isIddle(data, 1)) {
        stopPaddle(game.rightPaddle);
      }
      break;
    case 3: game.start(1); break;
    case 5: game.start(2); break;
  }
});

movePaddle = function(data, paddle, game) {
  if (data['y'] < 0) {
    paddle.stopMovingDown();
    paddle.moveUp();
  }
  if (data['y'] >= 0) {
    paddle.stopMovingUp();
    paddle.moveDown();
  }
}

stopPaddle = function(paddle) {
  paddle.stopMovingDown();
  paddle.stopMovingUp();
}
