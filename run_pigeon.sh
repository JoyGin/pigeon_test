# run_pigeon.sh

chmod a+rw "./ios"
chmod a+rw "./android"
androidPigeonPath="./android/app/src/main/java/com/example/pigeon"
iosPigeonPath="./ios/Runner"
androidPackage="com.example.pigeon"

if [ ! -d  androidPigeonPath ]; then
  echo 'Android 代码生成目录不存在，创建目录'
  mkdir -p "$androidPigeonPath"
else
  echo '安卓代码生成目录已存在'
fi

if [ ! -d iosPigeonPath ]; then
  echo 'iOS 代码生成目录不存在，创建目录'
  mkdir -p "$iosPigeonPath"
else
  echo 'iOS 代码生成目录已存在'
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