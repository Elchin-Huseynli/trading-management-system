# Trading Management System
# Introduction
- Welcome to the Trade Control System (TMS) README. This document provides an overview of the TMS application, its features, installation instructions, and usage guidelines. This project is a Spring Boot application designed to manage product, category, and supplier-related operations in a PostgreSQL database.

  The TMS application offers a comprehensive set of features to facilitate effective management of various aspects of your business.


- Product Management: This tool provides users with the ability to exercise complete authority over their product inventory. It simplifies the processes of product listing, updating, and removal, offering invaluable assistance to businesses aiming to streamline their inventory management.

- Category Handling: Through this feature, users can easily create, modify, and eliminate product categories. Furthermore, products can be linked with one or multiple categories, streamlining the process of filtering and searching for specific items.

- Supplier Management: This utility allows businesses to effectively oversee their associations with suppliers. Users can add, revise, or remove supplier details as necessary, and they can conveniently connect products with their relevant suppliers, ensuring swift access to supplier information when required.

- Data Integrity and Verification: To enhance data precision and reliability, this system incorporates customized validation regulations.


# Technology
- IDE - Intellij Idea
- JDK - 17
- Spring Web - 2.7.14
- Spring Data Jpa
- Poatgresql
- Mapstruct
- Lombok
- Validation
- Security
- JWT

# Structure
![trading](https://github.com/Elchin-Huseynli/trading-management-system/assets/116680886/a0cbb78d-ec5a-4756-84c7-516410e5ef14)




# Endpoint

**User**
- http://localhost:8080/tms/user/registration  [POST]
- http://localhost:8080/tms/user/login         [POST]
- http://localhost:8080/tms/user/refresh-token [GET]

 **Category**
- http://localhost:8080/tms/category/registration   [POST]
- http://localhost:8080/tms/category/all-categories [GET]

 **Supplier**
- http://localhost:8080/tms/supplier/registration  [POST]
- http://localhost:8080/tms/supplier/all-suppliers [GET]
  
 **Supplier**
- http://localhost:8080/tms/product/registration  [POST]
- http://localhost:8080/tms/product/all-products  [GET]
- http://localhost:8080/tms/product/{id}          [GET]
- http://localhost:8080/tms/product/{id}          [PUT]
- http://localhost:8080/tms/product/{id}          [DELETE]

<br>
<br>

**Security and Jwt**
<br>
<br>
The project is secured using JWT (JSON Web Token) technology. Initially, the user signs up, and during this process, the user is provided with an access token and a refresh token. When the user validates their registration with the email address they provided during the sign-up, the user's account is activated. Until the access token's expiration time (token duration), the user can access API endpoints such as product, supplier, and category. When the access token expires, the user can obtain a new access token by using the login endpoint to refresh their access token.

# Structure
![jwt](https://github.com/Elchin-Huseynli/trading-management-system/assets/116680886/41b1b282-696e-4dad-a87d-04ae44a4be4d)


<br>
<br>

  ![jwt](https://github.com/NihatQuliyev/spring-security-jwt/assets/116736363/7becbadf-a3a5-4c1b-9b7a-65f375020cde)

<br>
**User registration**
<br>

![Screenshot (381)](https://github.com/Elchin-Huseynli/trading-management-system/assets/116680886/327f9dd6-1fb2-49d7-8c08-08e00f089284)


<br>
<br>

**User refresh-token**
<br>

![Screenshot (383)](https://github.com/Elchin-Huseynli/trading-management-system/assets/116680886/628a27fe-c7c3-4af6-a4aa-a14b6c6f681f)


<br>
<br>

**User login**
<br>

![Screenshot (384)](https://github.com/Elchin-Huseynli/trading-management-system/assets/116680886/9b31974b-2f27-4fda-a893-6116edeb3298)



<br>
<br>

**Category registration**
<br>

![Screenshot (385)](https://github.com/Elchin-Huseynli/trading-management-system/assets/116680886/167f9648-3ef6-4ec4-9813-3652e6da76ad)


<br>
<br>

**Product registration**
<br>

![Screenshot (386)](https://github.com/Elchin-Huseynli/trading-management-system/assets/116680886/574522ca-5043-4c7c-8add-3315e56fcf0b)


<br>
<br>

**Product Show**
<br>

![Screenshot (387)](https://github.com/Elchin-Huseynli/trading-management-system/assets/116680886/531f3723-8171-409b-9a0c-cd7e8244b500)


<br>
<br>
**Product Update**
<br>
When updating the product, you can modify the desired fields while leaving the others unchanged. For example, in this case, the "price" has been modified.
<br>
<br>

![Screenshot (388)](https://github.com/Elchin-Huseynli/trading-management-system/assets/116680886/9891460f-7e97-493f-bc50-1a7f8459c3e2)
