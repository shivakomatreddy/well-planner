-- Users Schema

# --- !Ups
CREATE TABLE "users" ("id" integer PRIMARY KEY, "user_auth_0_id" varchar(100), "logged_in" boolean, "username" varchar(50), "password" varchar(100), "email" varchar(100), "business_id" integer, "is_admin" boolean, "is_customer" boolean, "is_an_employee" boolean, FOREIGN KEY("business_id") REFERENCES "businesses"(id), "modified_date" integer, "created_date" integer);

# --- !Downs
DROP TABLE "users";