// pigeons/message.dart

import 'package:pigeon/pigeon.dart';

class Book {
  String? title;
  String? author;
}

@HostApi()
abstract class BookApi {
  List<Book?> search(String keyword);

  List<Book?> find(String keyword);
}

@FlutterApi()
abstract class ColorApi {
  int updateColor(int color);
}
