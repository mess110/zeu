socket.on('controller_action', function (data) {
  if (data['input'] == 0) {
    movePaddle(data, game.leftPaddle, game);
  }
  if (data['input'] == 1) {
    movePaddle(data, game.rightPaddle, game);
  }
  if (data.hasOwnProperty('button_release')) {
    if (data['button_release'] == 3) {
      game.start(1);
    }
    if (data['button_release'] == 5) {
      game.start(2);
    }
  }
});

movePaddle = function(data, paddle, game) {
  if (data.hasOwnProperty('input')) {
    if (data['y'] < 0) {
      paddle.stopMovingDown()
      paddle.moveUp();
    }
    if (data['y'] >= 0) {
      paddle.stopMovingUp()
      paddle.moveDown();
    }
  }
  /*if (data.hasOwnProperty('button_release')) {
    switch(data['button_release']) {
    case 3:
      game.start(1);
      break;
    case 5:
      paddle.stopMovingUp()
      paddle.stopMovingDown()
      break;  
    }
  }*/
}

//$(game).simulate("keyup", { keyCode: 65 });
///$(game).simulate("keyup", { keyCode: 37 });
//console.log($("#game").trigger(press));
//console.log(game);
