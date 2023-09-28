import Flutter
import UIKit
import IOSSecuritySuite

public class RjsnifferPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "com.emrys.rjsniffer/epic", binaryMessenger: registrar.messenger())
    let instance = RjsnifferPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }


  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
        switch call.method {
        case "runprog":
            let amICompromised = IOSSecuritySuite.amIJailbroken()
            result(amICompromised)
            break
        case "runprog2":
            let amIEmulator = IOSSecuritySuite.amIRunInEmulator()
            result(amIEmulator)
            break
        case "runprog3":
            let amIDebugged = IOSSecuritySuite.amIDebugged()
            result(amIDebugged)
            break
        default:
            result(FlutterMethodNotImplemented)
        }
  }
}
