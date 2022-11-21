
# Notes and Assumptions

## Task

I have elected to implement the task:
- `Issuing an invoice for the buyer once the merchant notifies us the goods have been shipped`


## Notes

* The email of the talent acquisition specialist mentioned this task should take around one hour. I don't think that someone heading into any new code base would be able to implement most of these in an hour, not at least properly, might be able to hack it in that time. Probably the only one that an hour seems fair is `Adding an address to an organisation`
* It's been a while since I last worked with Spring and Kotlin so it was fun to play around with it again and remember a lot that I had forgotten. 😁
* Kudos on not using JPA(such as Hibernate) magic voodoo to generate your queries. although it makes things easy, it's too risky to let something as complicated as queries to be auto-generated, it's bound to make mistakes, at least that used to be my experience with Hibernate.
* Have never heard of `traefik` before, seems pretty cool.
* Given the suggest time to complete the task, I have not done everything I would have liked. I spent around 2 hours on this task. These are some of the things I would have liked to if I was to spend more time on it.
    * Would have liked to write some proper integration tests.
    * I have used `DECIMAL` to store money. As you might know, it's not always the best idea as large floating point number are prone to lose precision. In an ideal world I would have used `BIGINT` and store the amount in cents instead. However opted for basic normal decimals to keep things simple.
    * Would have like to spent some time on containerization. Some ideas
        * Use something like `TestContainers` to bring up a fresh database when running tests. Tests are meant to be easy to run as it does not discourage developers from writing fresh ones
        * Maybe a watcher implementation which would watch the migrations folder and re-deploy a fresh database when a new migration is added.
* Have used Mockito as it's the default that comes with spring-boot-test.  Upon consideration would have rather used `mockk`

## Assumptions

* Was not really sure what the requirement where so tried to keep things simple. Created a few tables to store the information and make up my own JSON schema.


