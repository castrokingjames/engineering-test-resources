# engineering-test-resources


This is a collection of a simple set of publicly available resources for engineers submitting technical tests to amaysim.


## What we're looking for
Here at amaysim we're all about creating beautiful, enriching and rewarding experiences for our customers. We value simplicity and reliability in how we develop our software. With that in mind we'd love for you to build a very simple app for us that our Product Manager wants to get up and running for some User Testing.


## Technical Test

Using our public `collection.json` as a source of customer data we want you to create an app that consumes data from the collection and does the following:

1. Create an initial login screen that allows a customer to login (we don't want you to actually create an authentication layer, just validate the MSN credentials in the collection)
2. Display a simple welcome to amaysim splash page
3. Transition through to a screen that shows the customer the following information:
  * information about their plan, also known as a subscription,
remaining data balance of their subscription
  * information about the product that is related to the subscription, including the name and price of the product
  * any other information you think may be relevant to the customer

### Hints

Our `collection.json` is based on one of our public facing API's. It's built to the specification of [http://jsonapi.org/](http://jsonapi.org/) which is the standard for developing API's that we use here at amaysim.

Here's some other handy tips:

* All data and data balances are represented in MB but should be presented to the customer in GB
* All monetary values are represented in cents but should be presented to the customer in dollar values

## Technical Guidance

1. Use the programming language and tooling of your choice, either Objective C/Swift/Java
2. Make your source code available on Github/Bitbucket (or the online repo of your choosing)
3. Try to avoid the use of frameworks (unless they're for testing)
4. Try not to spend more than 2-3 hours maximum. (We don't want you to lose a weekend over this!)
