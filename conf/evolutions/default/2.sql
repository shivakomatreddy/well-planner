-- Business Schema
# --- !Ups
CREATE TABLE "businesses" ("id" SERIAL PRIMARY KEY, "name" varchar(100), "city" varchar(100), "state" varchar(100), "country" varchar(100), "modifiedDate" timestamp, "createdDate" timestamp);

# --- !Downs
DROP TABLE "businesses";