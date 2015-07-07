var exec = require('cordova/exec');

exports.getContainerId = function(success, error) {
  exec(success, error, "GTMPlugin", "getContainerId", []);
};

exports.getBoolean = function(arg0, success, error) {
  exec(success, error, "GTMPlugin", "getBoolean", [arg0]);
};

exports.getDouble = function(arg0, success, error) {
  exec(success, error, "GTMPlugin", "getDouble", [arg0]);
};

exports.getLong = function(arg0, success, error) {
  exec(success, error, "GTMPlugin", "getLong", [arg0]);
};

exports.getString = function(arg0, success, error) {
  exec(success, error, "GTMPlugin", "getString", [arg0]);
};
