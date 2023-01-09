// Autogenerated from Pigeon (v5.0.0), do not edit directly.
// See also: https://pub.dev/packages/pigeon
#import "Message.h"
#import <Flutter/Flutter.h>

#if !__has_feature(objc_arc)
#error File requires ARC to be enabled.
#endif

static NSArray *wrapResult(id result, FlutterError *error) {
  if (error) {
    return @[ error.code ?: [NSNull null], error.message ?: [NSNull null], error.details ?: [NSNull null] ];
  }
  return @[ result ?: [NSNull null]  ];
}
static id GetNullableObject(NSDictionary* dict, id key) {
  id result = dict[key];
  return (result == [NSNull null]) ? nil : result;
}
static id GetNullableObjectAtIndex(NSArray* array, NSInteger key) {
  id result = array[key];
  return (result == [NSNull null]) ? nil : result;
}


@interface Book ()
+ (Book *)fromList:(NSArray *)list;
+ (nullable Book *)nullableFromList:(NSArray *)list;
- (NSArray *)toList;
@end

@implementation Book
+ (instancetype)makeWithTitle:(nullable NSString *)title
    author:(nullable NSString *)author {
  Book* pigeonResult = [[Book alloc] init];
  pigeonResult.title = title;
  pigeonResult.author = author;
  return pigeonResult;
}
+ (Book *)fromList:(NSArray *)list {
  Book *pigeonResult = [[Book alloc] init];
  pigeonResult.title = GetNullableObjectAtIndex(list, 0);
  pigeonResult.author = GetNullableObjectAtIndex(list, 1);
  return pigeonResult;
}
+ (nullable Book *)nullableFromList:(NSArray *)list { return (list) ? [Book fromList:list] : nil; }
- (NSArray *)toList {
  return @[
    (self.title ?: [NSNull null]),
    (self.author ?: [NSNull null]),
  ];
}
@end

@interface BookApiCodecReader : FlutterStandardReader
@end
@implementation BookApiCodecReader
- (nullable id)readValueOfType:(UInt8)type 
{
  switch (type) {
    case 128:     
      return [Book fromList:[self readValue]];
    
    default:    
      return [super readValueOfType:type];
    
  }
}
@end

@interface BookApiCodecWriter : FlutterStandardWriter
@end
@implementation BookApiCodecWriter
- (void)writeValue:(id)value 
{
  if ([value isKindOfClass:[Book class]]) {
    [self writeByte:128];
    [self writeValue:[value toList]];
  } else 
{
    [super writeValue:value];
  }
}
@end

@interface BookApiCodecReaderWriter : FlutterStandardReaderWriter
@end
@implementation BookApiCodecReaderWriter
- (FlutterStandardWriter *)writerWithData:(NSMutableData *)data {
  return [[BookApiCodecWriter alloc] initWithData:data];
}
- (FlutterStandardReader *)readerWithData:(NSData *)data {
  return [[BookApiCodecReader alloc] initWithData:data];
}
@end


NSObject<FlutterMessageCodec> *BookApiGetCodec() {
  static FlutterStandardMessageCodec *sSharedObject = nil;
  static dispatch_once_t sPred = 0;
  dispatch_once(&sPred, ^{
    BookApiCodecReaderWriter *readerWriter = [[BookApiCodecReaderWriter alloc] init];
    sSharedObject = [FlutterStandardMessageCodec codecWithReaderWriter:readerWriter];
  });
  return sSharedObject;
}

void BookApiSetup(id<FlutterBinaryMessenger> binaryMessenger, NSObject<BookApi> *api) {
  {
    FlutterBasicMessageChannel *channel =
      [[FlutterBasicMessageChannel alloc]
        initWithName:@"dev.flutter.pigeon.BookApi.search"
        binaryMessenger:binaryMessenger
        codec:BookApiGetCodec()];
    if (api) {
      NSCAssert([api respondsToSelector:@selector(searchKeyword:error:)], @"BookApi api (%@) doesn't respond to @selector(searchKeyword:error:)", api);
      [channel setMessageHandler:^(id _Nullable message, FlutterReply callback) {
        NSArray *args = message;
        NSString *arg_keyword = GetNullableObjectAtIndex(args, 0);
        FlutterError *error;
        NSArray<Book *> *output = [api searchKeyword:arg_keyword error:&error];
        callback(wrapResult(output, error));
      }];
    }
    else {
      [channel setMessageHandler:nil];
    }
  }
}
