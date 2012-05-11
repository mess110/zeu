// TODO find a better way
// viewports.length doesn't work
countHash = function(hash) {
  count = 0;
  for (var id in hash) {
    count++;
  }
  return count;
}

dc = function(hash, socket) {
  if (hash.hasOwnProperty(socket.id)) {
    delete hash[socket.id];
  } else {
    console.log('can not disconnect ' + socket.id);
  }
}
