-- Users Schema

# --- !Ups
CREATE TABLE "users" ("id" SERIAL PRIMARY KEY, "username" varchar(50), "password" varchar(25), "email" varchar(100), "businessId" SERIAL, "isAdmin" boolean, "isCustomer" boolean, "isAnEmployee" boolean, FOREIGN KEY("businessId") REFERENCES "businesses"(id), "modifiedDate" timestamp, "createdDate" timestamp);

# --- !Downs
DROP TABLE "users";