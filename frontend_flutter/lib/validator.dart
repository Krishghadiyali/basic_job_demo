class Validator {
  static String? name(String? value) {
    if (value == null) {
      return 'enter a name';
    }
    return null;
  }

  static String? email(String? Value) {
    if (Value == null || Value.isEmpty) {
      return 'enter an email';
    }

    final eref = RegExp(r'^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$');

    if (!eref.hasMatch(Value)) {
      return 'enter an valid email';
    }
    return null;
  }

  static String? password(String? val) {
    if (val == null || val.isEmpty) {
      return 'enter an password';
    }
    return null;
  }
}
