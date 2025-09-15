import 'package:flutter/material.dart';
import 'package:frontend_flutter/signup.dart';
import 'package:get/get.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const GetMaterialApp(home: SignupPage());
  }
}
