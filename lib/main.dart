import 'package:flutter/material.dart';

import 'message.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  late BookApi bookApi;
  Book? book;
  late ColorApi colorApi;
  int color = -1;

  void _incrementCounter() {
    setState(() {
      // _counter++;
      Future(() async {
        List<Book?> reply = await bookApi.search("Aaron");
        book = reply[0];
      });
    });
  }

  @override
  void initState() {
    super.initState();
    bookApi = BookApi();
    colorApi = ColorApiImpl(updateColorHandler: updateColor);
    ColorApi.setup(colorApi);
  }

  int updateColor(int color) {
    this.color = color;
    return color + 1000;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text('color: ${color}'),
            Text('book: ${book?.author} ${book?.title}'),
            const Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headline4,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}

class ColorApiImpl implements ColorApi {
  int Function(int) updateColorHandler;

  ColorApiImpl({required this.updateColorHandler});

  @override
  int updateColor(int color) {
    print('flutter updateColor $color');
    return updateColorHandler.call(color);
  }
}
