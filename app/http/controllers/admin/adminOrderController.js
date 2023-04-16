const Order = require("./../../../models/order.schema");
const User = require("./../../../models/user.schema");
const Menu = require("./../../../models/menu.schema");
var fs = require("fs");
var path = require("path");
const adminOrderController = () => {
  return {
    async index(req, res) {
      const totalOrder = await Order.countDocuments();
      const totalUser = await User.countDocuments();
      const totalMenu = await Menu.countDocuments();
      const total = {
        totalOrder,
        totalUser,
        totalMenu,
      };
      return res.render("admin/index", total);
    },
    async users(req, res) {
      const users = await User.find();
      return res.render("admin/users", { users });
    },
    async usersStatus(req, res) {
      const { userId } = req.params;
      const user = await User.findById(userId);
      user.active = !user.active;
      user.save();
      res.redirect("/admin/users");
    },
    async menus(req, res) {
      const menus = await Menu.find();
      return res.render("admin/menus", { menus });
    },
    async createMenu(req, res) {
      return res.render("admin/createMenu");
    },
    async saveMenu(req, res) {
      const menu = new Menu({
        name: req.body.name,
        price: req.body.price,
        size: req.body.size,
        image: req.file.filename,
      });
      await menu.save();
      res.redirect("/admin/menus");
    },
    async editMenu(req, res) {
      const { menuId } = req.params;
      const menu = await Menu.findById(menuId);
      return res.render("admin/editMenu", { menu });
    },
    async updateMenu(req, res) {
      try {
        const { menuId } = req.params;
        console.log("sss", req.body);
        const menu = await Menu.findById(menuId);
        menu.name = req.body.name;
        menu.price = req.body.price;
        menu.size = req.body.size;
        if (req?.file?.filename) {
          menu.image = req.file.filename;
        }
        await menu.save();
      } catch (err) {
        console.log(err);
      } finally {
        res.redirect("/admin/menus");
      }
    },
    async deleteMenu(req, res) {
      try {
        const { menuId } = req.params;
        const menu = await Menu.findByIdAndDelete(menuId);
        const filePath = path.join(
          __dirname,
          "/../../../../public/upload",
          menu.image
        );
        fs.unlinkSync(filePath);
      } catch (error) {
        console.log(error);
      } finally {
        res.redirect("/admin/menus");
      }
    },
    async order(req, res) {
      const orders = await Order.find({ status: { $ne: "completed" } }, null, {
        sort: { createdAt: -1 },
      })
        .populate("customerId", "-password")
        .exec((err, orders) => {
          if (req.xhr) {
            return res.json(orders);
          } else {
            return res.render("admin/order");
          }
        });
    },
  };
};

module.exports = adminOrderController;
