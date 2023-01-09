# run_pigeon.sh

chmod a+rw "./ios"
chmod a+rw "./android"
androidPigeonPath="./android/app/src/main/java/com/example/pigeon"
iosPigeonPath="./ios/Runner"
androidPackage="com.example.pigeon"

if [ ! -d  androidPigeonPath ]; then
  mkdir -p "$androidPigeonPath"
fi

if [ ! -d iosPigeonPath ]; then
  mkdir -p "$iosPigeonPath"
fi

echo '开始生成代码'
flutter pub run pigeon \
  --input pigeons/message.dart \
  --dart_out lib/message.dart \
  --objc_header_out "$iosPigeonPath/Message.h" \
  --objc_source_out "$iosPigeonPath/Message.m" \
  --java_out "$androidPigeonPath/Message.java" \
  --java_package "$androidPackage"
echo '生成代码结束'