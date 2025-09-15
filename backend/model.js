const mongodb = require('mongoose');

const userSchema = mongodb.Schema({
    username:{
        type:String,
        required:true
    },
    email:{
          type:String,
        required:true
    },
    password:{
          type:String,
        required:true
    }
});

module.exports = mongodb.model('user',userSchema);