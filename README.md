# UpgradeSample for Omar Sharifali

This is a Spring Boot project created with Maven that creates a Wallet API/Library. The API documentation can be found at
[https://documenter.getpostman.com/view/5778979/RzZ4qhfB]

To run it, simply clone the project and type `mvn spring-boot:run` in terminal. This will start a local server on port 8080 and you can now use your preferred method to interact with the API.



All requests in the document have been handled including not allowing to withdraw into a negative balance or transfering more many than what is in an account. All transactions have a unique ID and timestamp. JUnits have been written to tests coverage to make sure balances for multiple accounts are being maintained properly after performing various different types of transactions.  One thing to note is that performing any transactions such as getting the balance or depositting without specifying account number will be done from the default account (Account 0). Accounts range from 0 to n and are created as specified in the documentation.

I create this project in the little free time I had during my school week and I hope you guys like it!!
