import 'rjsniffer_platform_interface.dart';

class Rjsniffer {
  static Future<bool?> amICompromised() {
    return RjsnifferPlatform.instance.amICompromised();
  }

  static Future<bool?> amIEmulator() {
    return RjsnifferPlatform.instance.amIEmulator();
  }

  static Future<bool?> amIDebugged() {
    return RjsnifferPlatform.instance.amIDebugged();
  }
}
