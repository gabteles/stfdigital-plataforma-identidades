'use strict';

var path = require('path');
var conf = require('./../../../gulp/conf');

var _ = require('lodash');
var wiredep = require('wiredep');

function listIncludeFiles() {
  console.log(path.resolve(conf.paths.unit));
	var wiredepOptions = _.extend({}, conf.wiredep, {
	    dependencies: true,
	    devDependencies: true
	});

	var patterns = wiredep(wiredepOptions).js.map(function(pathz) {
    return '.' + path.resolve(pathz).replace(path.resolve(conf.paths.root), '').replace(/\\/g,"/");
  });
	
	patterns.push(path.join(conf.paths.unit, 'mock/**/*.js'));
	
	return patterns;
}

function listFiles() {
  var patterns = listIncludeFiles();
  
  patterns.push('src/main/resources/public/devolucao.js');
  
  var files = patterns.map(function(pattern) {
    return {
      pattern: pattern,
      included: false
    };
  });
  files.push(path.join(conf.paths.test, 'unit/app/**/*.js'));
  return files;
}
module.exports = function(config) {
  var configuration = {
    files: listFiles(),

    singleRun: true,
    
    basePath: '../../..',

    autoWatch: false,

    logLevel: 'info',

    frameworks: ['systemjs', 'jasmine'],

    browsers : ['Chrome'],

    plugins : [
      'karma-systemjs',
      'karma-phantomjs-launcher',
      'karma-chrome-launcher',
      //'karma-coverage',
      'karma-jasmine',
	  'karma-html-reporter',
	  'karma-mocha-reporter'
    ],

    coverageReporter: {
      type : 'html',
      dir : 'coverage/'
    },

    reporters: ['mocha', 'html'],

    htmlReporter : {
		outputDir : path.join(conf.paths.unit, 'results/html')
	},
    
    systemjs: {
    	configFile:  path.join(conf.paths.test, 'system.conf.js'),
    	serveFiles: ['src/main/resources/public/devolucao.js', 'src/main/resources/public/maps/devolucao.js.map'],
    	includeFiles: listIncludeFiles()
    },

    proxies: {
      '/base/recebimento/devolucao': '/base/src/main/resources/public/devolucao.js',
      '/base/recebimento/maps/devolucao.js.map': '/base/src/main/resources/public/maps/devolucao.js.map'
    }
  };

  // This is the default preprocessors configuration for a usage with Karma cli
  // The coverage preprocessor is added in gulp/unit-test.js only for single tests
  // It was not possible to do it there because karma doesn't let us now if we are
  // running a single test or not
//  configuration.preprocessors = {};
//  pathSrcHtml.forEach(function(path) {
//    configuration.preprocessors[path] = ['ng-html2js'];
//  });
//
  config.set(configuration);
};
