#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html.
# Run `pod lib lint root_jailbreak_sniffer.podspec` to validate before publishing.
#
Pod::Spec.new do |s|
  s.name             = 'root_jailbreak_sniffer'
  s.version          = '1.1.4'
  s.summary          = 'A Flutter Plugin to detect root on android and JailBreak on iOS'
  s.description      = <<-DESC
A Flutter Plugin to detect root on android and JailBreak on iOS
                       DESC
  s.homepage         = 'https://github.com/romikavinda/root_jailbreak_sniffer'
  s.license          = { :file => '../LICENSE' }
  s.author           = { 'romikavinda' => 'romikavinda@yahoo.com' }
  s.source           = { :path => '.' }
  s.source_files = 'Classes/**/*'
  s.dependency 'Flutter'
  s.dependency       'IOSSecuritySuite', '~> 1.9.10'
  s.platform = :ios, '10.0'

  # Flutter.framework does not contain a i386 slice.
  s.pod_target_xcconfig = { 'DEFINES_MODULE' => 'YES', 'EXCLUDED_ARCHS[sdk=iphonesimulator*]' => 'i386' }
  s.swift_version = '5.0'
end
