import 'package:flutter/material.dart';
import 'package:frontend_flutter/signup_controller.dart';
import 'package:frontend_flutter/validator.dart';
import 'package:get/get.dart';

class SignupPage extends StatelessWidget {
  const SignupPage({super.key});

  @override
  Widget build(BuildContext context) {
    final controller = Get.put(SignupController());
    return Scaffold(
      appBar: AppBar(title: Text("SignUp Page")),
      body: SingleChildScrollView(
        padding: EdgeInsets.all(20),
        child: Form(
          key: controller.formkey,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              TextFormField(
                validator: (Value) => Validator.name(Value),
                controller: controller.nameController,
                decoration: InputDecoration(labelText: 'user name'),
              ),
              SizedBox(height: 20),
              TextFormField(
                validator: (Value) => Validator.email(Value),
                controller: controller.emailController,
                decoration: InputDecoration(labelText: 'email'),
              ),
              SizedBox(height: 20),
              TextFormField(
                validator: (Value) => Validator.password(Value),
                controller: controller.passwordController,
                decoration: InputDecoration(labelText: 'password'),
              ),
              SizedBox(height: 20),
              ElevatedButton(
                onPressed: controller.signup,
                child: Text("SignUp"),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
