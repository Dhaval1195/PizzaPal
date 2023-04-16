module.exports = {
  PORT: process.env.PORT || 4000,
  MONGO_URI:
    process.env.MONGO_URI ||
    "mongodb://127.0.0.1:27017/real-time-pizza-order-app",
  COOKIE_SECRET: process.env.COOKIE_SECRET || "thisismysecret",
};
