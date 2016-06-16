var lazypipe = require('lazypipe');
var path = require('path');
var typescript = require('gulp-typescript');
var ngAnnotate = require('gulp-ng-annotate');
var embedTemplates = require('gulp-angular-embed-templates');
var moduleNameInjector = require('gulp-systemjs-module-name-injector');

var conf = require('../../../gulp/conf');

var createTsProject = function() {
	return typescript.createProject(path.join(conf.paths.src, 'tsconfig.json'));
};
var libraryTypeScript = path.join(conf.paths.src, 'typings/main/**/*.d.ts');

module.exports = {
  bundle: {
    'exemplo': {
      scripts: [path.join(conf.paths.app, 'exemplo.ts'),
                path.join(conf.paths.app, 'exemplo/**/*.ts'), libraryTypeScript],
      options: {
    	  rev: false,
    	  transforms: {
              scripts: lazypipe()
              	.pipe(typescript, createTsProject())
              	.pipe(moduleNameInjector, {rootDir: 'src/main/ui/app/', prefix: 'userauthentication/'})
              	.pipe(ngAnnotate)
              	.pipe(embedTemplates, {
              		skipErrors: true, 
              		minimize: {
              			empty : true,
              			spare : true,
              			quotes: true
              		}
              	})
          }
      }
    }
  },
  copy: [{
	  src : path.join(conf.paths.app, '**/*.json'),
      base: conf.paths.app
  }]
};
