require("dotenv").config();
const app = require("./app");
const config = require("./app/config");
require("./app/config/db").connectDB();
const Emitter = require("events");

const eventEmitter = new Emitter();
app.set("eventEmitter", eventEmitter);

const server = app.listen(config.PORT, () => {
  console.log(`Server listening on ${config.PORT}`);
});

// socket connection
const io = require("socket.io")(server);

io.on("connection", (socket) => {
  // Join the socket Server instance
  console.log(`Connecting to ${socket.id}`);

  socket.on("join", (orderId) => {
    socket.join(orderId);
  });
});

eventEmitter.on("orderUpdated", (data) => {
  io.to(`order_${data.id}`).emit("orderUpdated", data);
});
