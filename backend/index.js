const mongodb = require("mongoose");
const express = require("express");
const user = require("./model");

const app = express();
app.use(express.json());

const db = async ()=>{
    try {
        const connect = await mongodb.connect("mongodb://localhost:27017/basic_job_demo");
        console.log(`mongodb connected ${connect.connection.host}`);
    } catch (error) {
        console.error(error.message);
    }
}

db();
app.post("/basic_job_demo/signup",async(req,res)=>{

   const {username,email,password}=req.body;
   try {
    if (await user.findOne({email})) {
    return res.json({
        success:false,
        msg:"user alradey exists"
    });
  
}
  let User = new user();
    User.username = username;
    User.email = email;
    User.password = password;
    try {
        if (await User.save()) {
            return res.json({
                success:true,
                mse:"user signup"
            });
        }
    } catch (error) {
        console.error(error.message);
    }
   } catch (error) {
            console.error(error.message);
   }

});

// ✅ Start server
app.listen(3000, () => {
  console.log("Server running on port 3000");
});