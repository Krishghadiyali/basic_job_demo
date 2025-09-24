const mongodb = require("mongoose");
const express = require("express");
const user = require("./model");
const bcrypt = require("bcrypt");

const app = express();
app.use(express.json());

const db = async () => {
    try {
        const connect = await mongodb.connect("mongodb://localhost:27017/basic_job_demo");
        console.log(`mongodb connected ${connect.connection.host}`);
    } catch (error) {
        console.error(error.message);
    }
}

db();
app.post("/basic_job_demo/signup", async (req, res) => {

    const { username, email, password } = req.body;
    try {
        if (await user.findOne({ email })) {
            return res.json({
                success: false,
                msg: "user alradey exists"
            });

        }
        let User = new user();
        User.username = username;
        User.email = email;
        const salt = await bcrypt.genSalt(10);
        User.password = await bcrypt.hash(password, salt);
        try {
            if (await User.save()) {
                return res.json({
                    success: true,
                    mse: "user signup"
                });
            }
        } catch (error) {
            console.error(error.message);
        }
    } catch (error) {
        console.error(error.message);
    }

});

app.post("/basic_job_demo/login", async (req, res) => {
    const { email, password } = req.body;

    try {
        let existingUser = await user.findOne({ email });
        if (!existingUser) {
            return res.json({
                success: false,
                msg: "user not exist"
            });
        }
        if (!(await bcrypt.compare(password, existingUser.password))) {
            return res.json({
                success: false,
                msg: "wrong password"
            });

        }

        res.json({
            success: true,
            msg: "login successfull",
            user: {
                email: existingUser.email,
                password: existingUser.password
            }
        });
    } catch (error) {
        console.log(error);
        res.status(500).json({ success: false, msg: "internal server eroor" });
    }
})

app.get("/basic_job_demo/getdata", async (req, res) => {
    const { email } = req.query;
    try {
        const data = await user.findOne({ email });
        if (!data) {
            return res.json({
                success: false,
                msg: "data do not exsist"
            });

        }
        res.json({
            success: true,
            user: {
                username: data.username
            }
        });
    } catch (error) {
        console.log(error);
        res.status(500).json({ success: false, msg: "internal server error" });
    }
});


app.put("/basic_job_demo/update", async (req, res) => {
    const { email, username } = req.body;
    try {
        let existinguser = await user.findOne({email});
        if (!existinguser) {
            return res.json({
                "success": false,
                "msg": "user not found"
            });

        }
        if (username) {
            existinguser.username = username;
            await existinguser.save();
            return res.json({
                "success": true,
                "msg": "user is successfully updated"
            });
        }else {
            return res.json({
                success: false,
                msg: "no field to update"
            });
        }

    } catch (error) {
        return res.status(500).json({
            "success": false,
            "msg": "server error"
        });
    }
})

app.delete("/basic_job_demo/delete",async (req,res)=>{
    const {email}=req.body;
    try {
        let useraccount =await user.findOneAndDelete({email});
        if (useraccount) {
            return res.json({
                "success":true,
                "msg":"account deleted"
            });
        }else{
            return res.json({
                "success":false,
                "msg":"account not found"
            });
        }
    } catch (error) {
        return res.status(500).json({
            "success": false,
            "msg": "server error"
        });
    }
})
// ✅ Start server
app.listen(3000, () => {
    console.log("Server running on port 3000");
});