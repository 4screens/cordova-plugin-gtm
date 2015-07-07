#import <Foundation/Foundation.h>
#import <Cordova/CDV.h>

#import "TAGContainer.h"
#import "TAGContainerOpener.h"
#import "TAGManager.h"

@interface GTMPlugin : CDVPlugin <TAGContainerOpenerNotifier>

@property (nonatomic, strong) TAGManager *tagManager;
@property (nonatomic, strong) TAGContainer *container;

- (void)pluginInitialize;
- (void)getContainerId:(CDVInvokedUrlCommand*)command;
- (void)getBoolean:(CDVInvokedUrlCommand*)command;
- (void)getDouble:(CDVInvokedUrlCommand*)command;
- (void)getLong:(CDVInvokedUrlCommand*)command;
- (void)getString:(CDVInvokedUrlCommand*)command;

@end