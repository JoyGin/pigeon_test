#import "AppDelegate.h"
#import "GeneratedPluginRegistrant.h"
#import "Message.h"

@interface MyApi : NSObject <BookApi>
@end

@implementation MyApi
-(NSArray<Book *> *)searchKeyword:(NSString *)keyword error:(FlutterError **)error {
    static int i = 1;
    Book *result = [[Book alloc] init];
    result.title = [NSString stringWithFormat:@"%@'s Life %d", keyword, i++];
    result.author = keyword;
    return @[ result ];
}
@end

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application
didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    self.flutterEngine = [[FlutterEngine alloc] initWithName:@"my flutter engine"];
    [self.flutterEngine run];
    [GeneratedPluginRegistrant registerWithRegistry:self.flutterEngine];
    
    self.window = [[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
    [self.window makeKeyAndVisible];
    
    FlutterViewController *flutterViewController =
    [[FlutterViewController alloc] initWithEngine:self.flutterEngine nibName:nil bundle:nil];
    self.window.rootViewController = flutterViewController;
    
    MyApi *api = [[MyApi alloc] init];
    BookApiSetup(self.flutterEngine.binaryMessenger, api);
    
    // Override point for customization after application launch.
    return [super application:application didFinishLaunchingWithOptions:launchOptions];
}

@end
