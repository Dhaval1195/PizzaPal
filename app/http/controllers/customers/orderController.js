const Order = require("./../../../models/order.schema");
const moment = require("moment");
const orderController = () => {
  return {
    async index(req, res) {
      const orders = await Order.find({ customerId: req.user._id }, null, {
        sort: { createdAt: -1 },
      });
      res.header(
        "Cache-Control",
        "no-cache, private, no-store, must-revalidate, max-stale=0, post-check=0, pre-check=0"
      );
      res.render("customers/orders", { orders: orders, moment: moment });
    },
    store(req, res) {
      const { phone, address } = req.body;
      if (!phone || !address) {
        req.flash("error", "All Fields are required");
        return res.redirect("/cart");
      }

      const order = new Order({
        customerId: req.user._id,
        items: req.session.cart.items,
        phone: phone,
        address: address,
      });

      order
        .save()
        .then((result) => {
          req.flash("success", "Order Placed successfully");
          delete req.session.cart;
          return res.redirect("/customer/orders");
        })
        .catch((err) => {
          req.flash("error", "Something went wrong");
          return res.redirect("/cart");
        });
    },
    async show(req, res) {
      try {
        const order = await Order.findById(req.params.id);
        if (order) {
          if (req.user._id.toString() === order.customerId.toString()) {
            res.render("customers/singleOrder", { order: order });
          } else {
            res.send("404 not found");
          }
        } else {
          res.send("404 not found");
        }
      } catch (err) {
        res.send("404 not found");
      }
    },
  };
};

module.exports = orderController;
