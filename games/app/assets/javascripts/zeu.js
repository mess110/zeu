var socket = io.connect('http://localhost:5000');

socket.emit('viewport_register', {'game_id': '123'});
socket.on('controller_action', function (data) {
  theta += data['x'] * 10;
  //thetaY += data['y'] * 10;
});
