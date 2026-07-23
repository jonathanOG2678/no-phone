import 'dart:async';
import 'package:flutter/services.dart';


class NativeServiceBridge {
  static const MethodChannel _platform = MethodChannel('com.example.no_phone');

  //To be deleted, or use as a quick test before operations?
  Future<String> testConnection() async {
    final String? response = await _platform.invokeMethod<String>('testConnection');
  
    return response ?? 'No reponse received from Kotlin';
  }

  Future<bool> setMonitoringEnabled(bool enabled) async {
    final bool? response = await _platform.invokeMethod<bool>('setMonitoringEnabled', {'enabled': enabled,});
  
    return response ?? false;
  }

  Future<bool> getMonitoringEnabled() async {
    final bool? response = await _platform.invokeMethod<bool>('getMonitoringEnabled');

    return response ?? false;
  }


}