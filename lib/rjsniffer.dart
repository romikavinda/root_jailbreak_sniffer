import 'rjsniffer_platform_interface.dart';

class Rjsniffer {
  static Future<bool?> amICompromised() async {
    return await RjsnifferPlatform.instance.amICompromised();
  }

  static Future<bool?> amIEmulator() async {
    return await RjsnifferPlatform.instance.amIEmulator();
  }

  static Future<bool?> amIDebugged() async {
    return await RjsnifferPlatform.instance.amIDebugged();
  }
}
