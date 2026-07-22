import 'dart:async';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';


class NativeServiceBridge {
  static const MethodChannel _platform = MethodChannel('com.example.no_phone');

  Future<String> testConnection() async {
    final String? response = await _platform.invokeMethod<String>('testConnection');
  
    return response ?? 'No reponse received from Kotlin';
  }

}