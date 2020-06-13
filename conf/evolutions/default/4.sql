-- Customers Schema

# --- !Ups
CREATE TABLE "clients" ("id" SERIAL PRIMARY KEY, "name" varchar(50), "event_type" varchar(25), "email" varchar(100), "notes" varchar(1024), "budget"  double precision,
 "status" varchar(100), "business_id" SERIAL, FOREIGN KEY("business_id") REFERENCES "businesses"(id), "modified_date" numeric, "created_date" numeric);

# --- !Downs
DROP TABLE "clients";