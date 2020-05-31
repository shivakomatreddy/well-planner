-- Business Schema
# --- !Ups
CREATE TABLE "businesses" ("id" SERIAL PRIMARY KEY, "name" varchar(100), "city" varchar(100), "phone_number" varchar(100), "state" varchar(100), "country" varchar(100), "modified_date" integer, "created_date" integer);

# --- !Downs
DROP TABLE "businesses";