import 'package:flutter/cupertino.dart';
import 'package:frontend_flutter/apiservice.dart';
import 'package:get/get.dart';

class SignupController extends GetxController {
  final nameController = TextEditingController();
  final emailController = TextEditingController();
  final passwordController = TextEditingController();
  final formkey = GlobalKey<FormState>();

  Future<void> signup() async {
    final Map<String, dynamic> res = await Apiservice.signup(
      nameController.text.trim(),
      emailController.text.trim(),
      passwordController.text.trim(),
    );

    if (res['success'] == true) {
      Get.snackbar('Sucess', res['msg'], snackPosition: SnackPosition.BOTTOM);
    } else {
      Get.snackbar('Error', res['msg'], snackPosition: SnackPosition.BOTTOM);
    }
  }

  @override
  void onClose() {
    nameController.dispose();
    emailController.dispose();
    passwordController.dispose();
    // TODO: implement onClose
    super.onClose();
  }
}
