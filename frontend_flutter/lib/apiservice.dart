import 'dart:convert';
import 'package:http/http.dart' as http;

class Apiservice {
  static const String baseUrl = "http://192.168.152.112:3000/basic_job_demo";

  static Future<Map<String, dynamic>> signup(
    String username,
    String email,
    String password,
  ) async {
    try {
      final res = await http.post(
        Uri.parse('${baseUrl}/signup'),
        headers: {'Content-Type': 'application/json'},
        body: jsonEncode({
          'username': username,
          'email': email,
          'password': password,
        }),
      );
      return jsonDecode(res.body);
    } catch (e) {
      return {'Success': false, 'msg': 'Error connecting to server'};
    }
  }
}
