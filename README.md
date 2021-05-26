# mongodb-issue-demonstration

This repo is to demonstrate an issue when upgrading `spring-data-mongodb` from version `3.0.7.RELEASE` to
`3.0.8.RELEASE` or later.

It was put together following the guidelines at https://spring.io/guides/gs/accessing-data-mongodb/.
The Customer data model was tweaked slightly to demonstrate the issue we discovered.

## Reproducing the issue

1. Clone this repo
2. Make sure you have an instance of Mongo running locally
3. Run `mvn package` and view the output, you'll see:

```
Customers found with findAll():
-------------------------------
Customer(id=null, firstName=Alice, lastName=Smith, additionalData=null)
Customer(id=null, firstName=Bob, lastName=Smith, additionalData=null)
Customer(id=null, firstName=Mary, lastName=Smith, additionalData={a=DataValue(name=a, value=b)})

Customer found with findByFirstName('Alice'):
--------------------------------
Customer(id=null, firstName=Alice, lastName=Smith, additionalData=null)
Customers found with findByLastName('Smith'):
--------------------------------
Customer(id=null, firstName=Alice, lastName=Smith, additionalData=null)
Customer(id=null, firstName=Bob, lastName=Smith, additionalData=null)
Customer(id=null, firstName=Mary, lastName=Smith, additionalData={a=DataValue(name=a, value=b)})

Customers found with additionalData('a=b'):
--------------------------------
2021-05-26 13:58:29.633  INFO 41197 --- [           main] c.o.m.p.CustomerRepositoryImpl           : Mongo query: Query: { "$and" : [{ "additionalData.name" : "a"}, { "additionalData.value" : "b"}]}, Fields: {}, Sort: {}
### No customers found! ###
```

## Demonstrating that it used to work

1. Edit the pom.xml in this directory to change the spring-boot parent version from `2.3.10.RELEASE` to `2.3.9.RELEASE`
2. Run `mvn package` again and view the output, and you'll see:

```
Customers found with findAll():
-------------------------------
Customer(id=null, firstName=Alice, lastName=Smith, additionalData=null)
Customer(id=null, firstName=Bob, lastName=Smith, additionalData=null)
Customer(id=null, firstName=Mary, lastName=Smith, additionalData={a=DataValue(name=a, value=b)})

Customer found with findByFirstName('Alice'):
--------------------------------
Customer(id=null, firstName=Alice, lastName=Smith, additionalData=null)
Customers found with findByLastName('Smith'):
--------------------------------
Customer(id=null, firstName=Alice, lastName=Smith, additionalData=null)
Customer(id=null, firstName=Bob, lastName=Smith, additionalData=null)
Customer(id=null, firstName=Mary, lastName=Smith, additionalData={a=DataValue(name=a, value=b)})

Customers found with additionalData('a=b'):
--------------------------------
2021-05-26 14:05:07.566  INFO 41317 --- [           main] c.o.m.p.CustomerRepositoryImpl           : Mongo query: Query: { "$and" : [{ "additionalData.name" : "a"}, { "additionalData.value" : "b"}]}, Fields: {}, Sort: {}
Customer(id=null, firstName=Mary, lastName=Smith, additionalData={a=DataValue(name=a, value=b)})
```

## Highlighting the problem
1.  Note that the "Mongo query" logged by `CustomerRepositoryImpl` is identical in both cases
2.  Note that running this query directly against Mongo via the shell, e.g.
    `db.getCollection('customer').find({ "$and" : [{ "additionalData.name" : "a"}, { "additionalData.value" : "b"}]})`
    works fine, returning the Mary Smith result as expected
3.  I therefore conclude this to be a regression in spring-data-mongodb.
