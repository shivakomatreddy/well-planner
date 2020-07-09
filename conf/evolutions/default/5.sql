-- Projects Schema

# --- !Ups
CREATE TABLE "projects" ("id" SERIAL PRIMARY KEY, "name" varchar(50), "event_type" varchar(25), "brides_name" varchar(100), "grooms_name" varchar(100), "notes" varchar(1024), "budget" double precision, "business_id" SERIAL, FOREIGN KEY("business_id") REFERENCES "businesses"(id), "modified_date" numeric, "created_date" numeric);

# --- !Downs
DROP TABLE "projects";