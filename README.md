Composer Java Bindings
======================

A Java API for the PHP dependency manager [Composer](http://getcomposer.org/) and it's main
repository [packagist.org](http://packagist.org/).

See the [tests](https://github.com/pulse00/Composer-Java-Bindings/tree/master/src/test/java/org/getcomposer/test) for usage details.

An example implementation can be found in the [Eclipse Composer Plugin](https://github.com/pulse00/Composer-Eclipse-Plugin)

[![Build Status](https://secure.travis-ci.org/pulse00/Composer-Java-Bindings.png)](http://travis-ci.org/pulse00/Composer-Java-Bindings)


### Contributors

- [Thomas Gossmann](https://github.com/gossi)

API Notes
---------

### Naming Conventions

#### Resource
A file containing a package or a repository.

#### Repository
Contains multiple packages.

##### Repositories
* PackageRepository<br>
  Schema: n/a<br>
  Example: https://packagist.org/packages/gossi/ldap.json

* ComposerRepository<br>
  Schema: n/a<br>
  Example: https://packagist.org/p/packages-2011.json
   
* VcsRepository, GitRepository, SubversionRepository, MercurialRepository<br>
  Schema: http://getcomposer.org/doc/05-repositories.md#vcs<br>
  Example: http://getcomposer.org/doc/05-repositories.md#vcs

* PearRepository<br>
  Schema: http://getcomposer.org/doc/05-repositories.md#pear<br>
  Example: http://getcomposer.org/doc/05-repositories.md#pear

#### Package
Contains a single package may have different versions.


##### Packages

* ComposerPackage<br>
  Schema: http://getcomposer.org/doc/04-schema.md
  
* RepositoryPackage<br>
  Schema: n/a<br>
  Example: `package` in https://packagist.org/packages/gossi/ldap.json

#### Entity
Every objects or key value pairs as long as they can be grouped in a collection (see below).

##### Examples
```
"require": {
	"php": ">=5.3.0",
	"monolog/monolog": "1.0"
}
```
Each require entry is an entity (key-value pair).

```
"auhtors": [
	{
		"name": "gossi"
	}, 
	{
		"name": "Robert Gründler"
	}
]
```
Each entry in the authors is an entity.

#### Collections
Entities of the same type under the same parent.

#### Properties
Single key value pairs, that aren't entities.

##### Examples
```
{
	"name": "gossi/ldap"
}
```
The name property