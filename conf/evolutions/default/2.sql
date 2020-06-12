-- Business Schema
# --- !Ups
CREATE TABLE "businesses" ("id" SERIAL PRIMARY KEY, "name" varchar(100), "city" varchar(100), "phone_number" varchar(100), "state" varchar(100), "country" varchar(100), "modified_date" integer, "created_date" integer);

insert into businesses (name, city, phone_number, state, country, modified_date, created_date) values ('Carl', 'Minneapolis',123455 ,'MN','USA',20200610, 20200610);

# --- !Downs
DROP TABLE "businesses";