-- Customers Schema

# --- !Ups
CREATE TABLE "clients" ("id" SERIAL PRIMARY KEY, "name" varchar(50), "eventType" varchar(25), "email" varchar(100), "notes" varchar(1024), "budget"  double precision,
 "status" varchar(100), "businessId" SERIAL, FOREIGN KEY("businessId") REFERENCES "businesses"(id), "modifiedDate" timestamp, "createdDate" timestamp);

# --- !Downs
DROP TABLE "clients";